package com.db.demoapp.ui.etc.healthconnect.test;

import static java.util.Collections.emptySet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.health.connect.client.HealthConnectClient;
import androidx.health.connect.client.PermissionController;
import androidx.health.connect.client.aggregate.AggregationResult;
import androidx.health.connect.client.permission.HealthPermission;
import androidx.health.connect.client.records.StepsRecord;
import androidx.health.connect.client.request.AggregateRequest;
import androidx.health.connect.client.request.ChangesTokenRequest;
import androidx.health.connect.client.request.ReadRecordsRequest;
import androidx.health.connect.client.response.ReadRecordsResponse;
import androidx.health.connect.client.time.TimeRangeFilter;

import com.db.demoapp.R;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.JvmClassMappingKt;

public class HealthConnectTestActivity extends AppCompatActivity {
    private static final String TAG = "CHOI";
    private final String providerPackageName = "com.google.android.apps.healthdata";
    private HealthConnectClient healthConnectClient;
    private TextView textViewSteps;
    private static final Set<String> PERMISSIONS = new HashSet<>(List.of(
            HealthPermission.getReadPermission(JvmClassMappingKt.getKotlinClass(StepsRecord.class)),
            HealthPermission.getWritePermission(JvmClassMappingKt.getKotlinClass(StepsRecord.class))
    ));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_connect);
        Button buttonHealthConnect = findViewById(R.id.buttonHealthConnect);
        buttonHealthConnect.setOnClickListener(v -> startHealthConnectProcess());

        ImageButton fab = findViewById(R.id.fabCode);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.db.demoapp.code.DynamicTabbedCodeViewActivity.class);
            intent.putExtra("feature", "healthconnect"); // ✅ 핵심 포인트
            startActivity(intent);
        });
    }

    private void startHealthConnectProcess() {
        testHealthApi();
    }

    private void testHealthApi() {
        try {
            int availabilityStatus = HealthConnectClient.getSdkStatus(this, providerPackageName);
            Log.e(TAG, "testHealthApi: availabilityStatus>>>" + availabilityStatus);
            if (availabilityStatus == HealthConnectClient.SDK_AVAILABLE) {
                healthConnectClient = HealthConnectClient.getOrCreate(this);
                checkHealthPermission();
            } else if (availabilityStatus == HealthConnectClient.SDK_UNAVAILABLE_PROVIDER_UPDATE_REQUIRED) {
                redirectToProviderUpdate();
            } else {
                Log.e(TAG, "Health Connect is not available");
            }
        } catch (Exception e) {
            Log.e(TAG, "testHealthApi: " + e.toString());
            e.printStackTrace();
        }
    }

    private void checkHealthPermission() {
        try {
            Log.e(TAG, "checkHealthPermission: checkHealthPermission init()");
            requestPermissions.launch(PERMISSIONS);
        } catch (Exception e) {
            Log.e(TAG, "Error creating permission request intent", e);
        }
    }

    private final ActivityResultLauncher<Set<String>> requestPermissions = registerForActivityResult(
            PermissionController.createRequestPermissionResultContract(),
            granted -> {
                try {
                    if (granted.containsAll(PERMISSIONS)) {
                        Log.e(TAG, "requestPermissions success ==== ");
                        updateStepCounts();
                    } else {
                        Log.e(TAG, "requestPermissions fail ==== ");
                        showPermissionDeniedDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    );

    private void showPermissionDeniedDialog() {
        new AlertDialog.Builder(this)
                .setTitle("권한 필요")
                .setMessage("걸음 수 데이터를 사용하려면 권한이 필요합니다.\n설정에서 권한을 허용해주세요.")
                .setPositiveButton("설정으로 이동", (dialog, which) -> openHealthConnectSettings())
                .setNegativeButton("다시 시도", (dialog, which) -> requestPermissions.launch(PERMISSIONS))
                .setCancelable(false)
                .show();
    }

    private void openHealthConnectSettings() {
        Intent intent = new Intent();
        intent.setAction("android.health.connect.action.HEALTH_CONNECT_SETTINGS");
        // Android 13 이하에서는 Health Connect 앱 패키지명 지정 필요
        intent.setPackage("com.google.android.apps.healthdata");
        try {
            startActivity(intent);
        } catch (Exception e) {
            // Health Connect 앱이 없을 때 Play스토어로 유도
            redirectToProviderUpdate();
        }
    }

    private void redirectToProviderUpdate() {
        Log.e(TAG, "redirectToProviderUpdate: init");
        String uriString = "market://details?id=" + providerPackageName + "&url=healthconnect%3A%2F%2Fonboarding";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage("com.android.vending");
        intent.setData(Uri.parse(uriString));
        intent.putExtra("overlay", true);
        intent.putExtra("callerId", getPackageName());
        startActivity(intent);
    }

    private void updateStepCounts() throws Exception {
        ZoneId koreaZone = ZoneId.of("Asia/Seoul");
        Log.e(TAG, "updateStepCounts: koreaZone>>>"+ koreaZone );
        ZonedDateTime now = ZonedDateTime.now(koreaZone);
        Log.e(TAG, "updateStepCounts: now>>>"+ now );
        aggregateSteps(healthConnectClient, getInstantMap(now, now, "오늘"), "", R.id.textViewTodaySteps);
        aggregateSteps(healthConnectClient, getInstantMap(now.minusDays(1), now.minusDays(1), "전일"), "어제", R.id.textViewYesterdaySteps);
        aggregateSteps(healthConnectClient, getInstantMap(now.minusDays(6), now, "7일 전"), "7일", R.id.textViewSevenDaysSteps);
        aggregateSteps(healthConnectClient, getInstantMap(now.minusDays(29), now, "30일 전"), "30일", R.id.textViewThirtyDaysSteps);
        aggregateSteps(healthConnectClient, getInstantMap(now.minusDays(89), now, "90일 전"), "90일", R.id.textViewNinetyDaysSteps);
    }

    private void readStepsForPeriod(HealthConnectClient healthConnectClient, Instant startTime, Instant endTime,
                                    String periodName, int textViewId) {
        TimeRangeFilter timeRangeFilter = TimeRangeFilter.between(startTime, endTime);
        ReadRecordsRequest<StepsRecord> request = new ReadRecordsRequest<>(
                JvmClassMappingKt.getKotlinClass(StepsRecord.class),
                timeRangeFilter,
                emptySet(), false, 1000, null
        );
        Log.e(TAG, "readStepsForPeriod: [Data Set]\n startTime>>>" +
                (startTime) +", endTime>>>" + endTime);
        healthConnectClient.readRecords(request, new Continuation<ReadRecordsResponse<StepsRecord>>() {
            @NonNull
            @Override
            public CoroutineContext getContext() {
                return EmptyCoroutineContext.INSTANCE;
            }
            @Override
            public void resumeWith(@NonNull Object o) {
                try {
                    ReadRecordsResponse<StepsRecord> response = (ReadRecordsResponse<StepsRecord>) o;
                    long totalSteps = 0;
                    Log.e(TAG, "resumeWith: response.getRecords()>>>" + response.getRecords() );
                    for (StepsRecord stepRecord : response.getRecords()) {
                        totalSteps += stepRecord.getCount();
                    }
                    final long steps = totalSteps;
                    Log.e(TAG, "resumeWith: "+periodName+" 걸음 수 >>"+steps );
                    runOnUiThread(() -> {
                        TextView textView = findViewById(textViewId);
                        textView.setText(periodName + " 걸음 수: " + steps);
                    });
                } catch (Throwable throwable) {
                    Log.e(TAG, "Error reading step records for " + periodName, throwable);
                }
            }
        });
    }

    private void aggregateSteps(HealthConnectClient healthConnectClient, Map<String, Instant> dateMap, String periodName, int textViewId) throws Exception {
        try {
            Instant startTime = dateMap.get("startDateInstant");
            Instant endTime = dateMap.get("endDateInstant");
            Log.e(TAG, "readStepsForPeriod: [Data Set]\n"+periodName+" startTime>>>"
                    + (startTime) +", endTime>>>" + endTime);
            AggregateRequest aggregateRequest = new AggregateRequest(
                    Collections.singleton(StepsRecord.COUNT_TOTAL),
                    TimeRangeFilter.between(startTime, endTime),
                    Collections.emptySet()
            );
            healthConnectClient.aggregate(aggregateRequest, new Continuation<AggregationResult>() {
                @NonNull
                @Override
                public CoroutineContext getContext() {
                    return EmptyCoroutineContext.INSTANCE;
                }
                @SuppressLint("SetTextI18n")
                @Override
                public void resumeWith(@NonNull Object o) {
                    if (o instanceof AggregationResult result) {
                        Long steps = result.get(StepsRecord.COUNT_TOTAL);
                        Log.e(TAG, "resumeWith: "+periodName+" 걸음 수 >>"+ steps );
                        if(steps == null){
                            steps = 0L;
                        }
                        Long finalSteps = steps;
                        runOnUiThread(() -> {
                            TextView textView = findViewById(textViewId);
                            if(!"".equals(periodName)) {
                                textView.setText(periodName + ": " + finalSteps);
                            }else{
                                textView.setText(finalSteps.toString());
                            }
                        });
                    } else if (o instanceof Throwable) {
                        ((Throwable) o).printStackTrace();
                    }
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void getChangesToken() {
        Log.e(TAG, "getChangesToken: init");
        ChangesTokenRequest request = new ChangesTokenRequest(
                Collections.singleton(JvmClassMappingKt.getKotlinClass(StepsRecord.class)),
                Collections.emptySet()
        );
        healthConnectClient.getChangesToken(request, new Continuation<String>() {
            @NonNull
            @Override
            public CoroutineContext getContext() {
                return EmptyCoroutineContext.INSTANCE;
            }
            @Override
            public void resumeWith(@NonNull Object o) {
                if (o instanceof String) {
                    String token = (String) o;
                    Log.d(TAG, "Changes token: " + token);
                } else if (o instanceof Throwable) {
                    Throwable error = (Throwable) o;
                    Log.e(TAG, "Error getting changes token", error);
                }
            }
        });
    }

    private void processChanges(String initialToken) {
        Log.e(TAG, "processChanges: init" );
        AtomicReference<String> nextChangesTokenRef = new AtomicReference<>(initialToken);
        AtomicBoolean hasMore = new AtomicBoolean(true);
        while (hasMore.get()) {
            healthConnectClient.getChanges(nextChangesTokenRef.get(), new Continuation<>() {
                @NonNull
                @Override
                public CoroutineContext getContext() {
                    return EmptyCoroutineContext.INSTANCE;
                }
                @Override
                public void resumeWith(@NonNull Object o) {
                    if (o instanceof String) {
                        String token = (String) o;
                        Log.d(TAG, "New changes token: " + token);
                        nextChangesTokenRef.set(token);
                        hasMore.set(!token.isEmpty());
                    } else if (o instanceof Throwable) {
                        Throwable error = (Throwable) o;
                        Log.e(TAG, "Error getting changes", error);
                        hasMore.set(false);
                    }
                }
            });
        }
    }

    private static Map<String, Instant> getInstantMap(ZonedDateTime startDate, ZonedDateTime endDate, String description) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedStartDate = startDate.format(formatter);
        String formattedEndDate = endDate.format(formatter);
        ZonedDateTime startOfDay = startDate.truncatedTo(ChronoUnit.DAYS);
        ZonedDateTime endOfDay = endDate.truncatedTo(ChronoUnit.DAYS).plusDays(1).minusNanos(1);
        Instant startInstant = startOfDay.toInstant();
        Instant endInstant = endOfDay.toInstant();
        Map<String, Instant> dateMap = new HashMap<>();
        dateMap.put("startDateInstant", startInstant);
        dateMap.put("endDateInstant", endInstant);
        System.out.println(description + ":");
        System.out.println("  시작 날짜: " + formattedStartDate);
        System.out.println("  끝 날짜: " + formattedEndDate);
        System.out.println("  시작 시간: " + startInstant);
        System.out.println("  끝 시간: " + endInstant);
        System.out.println();
        return dateMap;
    }
}
