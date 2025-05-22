package com.db.demoapp.ui.threedmotion.test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.db.demoapp.R;

public class GeneralThreeDMotion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3d_viewer);

        Button btn3d = findViewById(R.id.btn3d);
        btn3d.setOnClickListener(v -> {
            // 구글 Scene Viewer로 3D 모델 띄우기
            Intent sceneViewerIntent = new Intent(Intent.ACTION_VIEW);
            sceneViewerIntent.setData(Uri.parse(
                    "https://arvr.google.com/scene-viewer/1.0" +
                            "?file=https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/Avocado/glTF/Avocado.gltf" +
                            "&mode=3d_only" +
                            "&title=Avocado"
            ));
            sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox");
            startActivity(sceneViewerIntent);
        });

        ImageButton fab = findViewById(R.id.fabCode);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.db.demoapp.code.DynamicTabbedCodeViewActivity.class);
            intent.putExtra("feature", "modal_snackbar"); // ✅ 핵심 포인트
            startActivity(intent);
        });
    }
}