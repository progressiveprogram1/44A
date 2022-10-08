package com.example.rkuandpeer;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Splashscreen extends AppCompatActivity {
    ImageView imageView;
    TextView nameTv;
    long animTime=2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

        getSupportActionBar().hide();

        imageView=findViewById(R.id.logo_splash);
        nameTv=findViewById(R.id.splash_name);

        ObjectAnimator animatorY=ObjectAnimator.ofFloat(imageView,"y",400f);
        ObjectAnimator animatorname=ObjectAnimator.ofFloat(nameTv,"x",370f);
        animatorY.setDuration(animTime);
        animatorname.setDuration(animTime);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(animatorY,animatorname);
        animatorSet.start();



        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent = new Intent(Splashscreen.this, login.class);
                startActivity(splashIntent);
                finish();

            }
        },4000);



    }
}