package com.example.visitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//public class SignupActivity extends AppCompatActivity {
//    private TextView tvSignUp;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
//
//        tvSignUp = findViewById(R.id.tvSignUp);
//
//        tvSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(SignupActivity.this, login_activity.class);
//                startActivity(i);
//            }
//        });
//
//    }
//}

public class SignupActivity extends AppCompatActivity {

    EditText email, username, password;
    Button btnSignUp;
    private TextView tvSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.etEmail);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvSignIn = findViewById(R.id.tvSignIn);

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignupActivity.this, login_activity.class);
                startActivity(i);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(SignupActivity.this, "Semua Kolom mohon diisi", Toast.LENGTH_LONG).show();
                } else{
                    signup();
                }
            }
        });
    }

    public void signup(){
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail(email.getText().toString());
        signupRequest.setUsername(username.getText().toString());
        signupRequest.setPassword(password.getText().toString());

        Call<SignupResponse> signupResponseCall = ApiClient.getUserService().userSignup(signupRequest);
        signupResponseCall.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(SignupActivity.this,"Sign Up Successful", Toast.LENGTH_LONG).show();
                    SignupResponse signupResponse =  response.body();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(SignupActivity.this, login_activity.class));
                        }
                    }, 700);

                }else{
                    Toast.makeText(SignupActivity.this,"Sign up gagal. Coba lagi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(SignupActivity.this,"Throwable"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}