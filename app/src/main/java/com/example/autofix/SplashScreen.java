package com.example.autofix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ImageView img = findViewById(R.id.imgLogo);
        TextView txt = findViewById(R.id.txtApp);

        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0.5f,
                1.0f,
                0.5f,
                1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(3000);
        img.startAnimation(scaleAnimation);
        txt.startAnimation(scaleAnimation);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
//                img.setColorFilter(R.color.myBlue, PorterDuff.Mode.ADD);
                finish();
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
            }
        };

        Timer opening = new Timer();
        opening.schedule(task,5000);
    }
}