package com.example.visitapp;

import androidx.appcompat.app.AppCompatActivity;

public class GetPrefId extends AppCompatActivity {

        SharedPrefManager sharedPrefManager = new SharedPrefManager(this);
        private String idPref = sharedPrefManager.getId();

        public final String idPref() {
                return idPref();
        }

}
