package com.example.visitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.visitapp.ui.ViewDataActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Handler handler = new Handler();
        final SharedPrefManager sharedPrefManager = new SharedPrefManager(this);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedPrefManager.getIsLogin()){
                    if (sharedPrefManager.getUsername().equals("admin")){
                        Intent i = new Intent(SplashScreen.this, ViewDataActivity.class);
                        finishAffinity();
                        startActivity(i);
                    }else {
                        Intent i = new Intent(SplashScreen.this, MainActivity.class);
                        finishAffinity();
                        startActivity(i);
                    }
                }else{
                    Intent i = new Intent(SplashScreen.this, login_activity.class);
                    finishAffinity();
                    startActivity(i);
                }
            }
        }, 3000);
    }
}