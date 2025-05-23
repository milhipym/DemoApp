package com.db.demoapp.ui.microinteraction;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import com.google.android.material.card.MaterialCardView;
import com.db.demoapp.R;

public class MicroSpringActivity extends AppCompatActivity {

    private MaterialCardView animatedCard;
    private float startY;
    private boolean isDragging = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.microspring_activity_main);
        animatedCard = findViewById(R.id.animatedCard);

        View rootLayout = findViewById(R.id.rootLayout);
        rootLayout.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startY = event.getY();
                    isDragging = true;
                    return true;

                case MotionEvent.ACTION_MOVE:
                    if (isDragging) {
                        float dy = event.getY() - startY;
                        dy = Math.max(0, dy);
                        animatedCard.setTranslationY(dy / 2);
                        animatedCard.setRadius(Math.min(dy / 2, 80));
                    }
                    return true;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    if (isDragging) {
                        isDragging = false;

                        SpringAnimation transAnim = new SpringAnimation(animatedCard, SpringAnimation.TRANSLATION_Y, 0);
                        SpringForce spring = new SpringForce(0);
                        spring.setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY);
                        spring.setStiffness(SpringForce.STIFFNESS_LOW);
                        transAnim.setSpring(spring);
                        transAnim.start();

                        animatedCard.animate().setDuration(300).withEndAction(() ->
                                animatedCard.setRadius(0)).start();
                    }
                    return true;
            }
            return false;
        });
    }
}