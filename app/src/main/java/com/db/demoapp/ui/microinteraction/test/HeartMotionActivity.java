package com.db.demoapp.ui.microinteraction.test;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import com.db.demoapp.R;

public class HeartMotionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_toggle);

        MotionLayout motionLayout = findViewById(R.id.motionLayout);
        motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {}

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {}

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                if (currentId == R.id.liked) {
                    Toast.makeText(HeartMotionActivity.this, "좋아요!", Toast.LENGTH_SHORT).show();
                } else if (currentId == R.id.unliked) {
                    Toast.makeText(HeartMotionActivity.this, "좋아요 취소!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {}
        });


    }
}
