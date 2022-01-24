package com.example.visitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Fragment;

import com.example.visitapp.ui.ViewDataActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//public class login_activity extends AppCompatActivity {
//    private Handler handler = new Handler();
//    private SharedPrefManager sharedPrefManager;
//    private EditText etUsername;
//    private EditText etPassword;
//    private Button btnSignIn;
//    private ProgressBar pbLogin;
//    private TextView tvSignUp;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        sharedPrefManager = new SharedPrefManager(this);
//        etUsername = findViewById(R.id.etUsername);
//        etPassword = findViewById(R.id.etPassword);
//        btnSignIn = findViewById(R.id.btnSignIn);
//        pbLogin = findViewById(R.id.pbLogin);
//        tvSignUp = findViewById(R.id.tvSignUp);
//
//        tvSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(login_activity.this, SignupActivity.class);
//                startActivity(i);
//            }
//        });
//
//        login();
//    }
//
//    private void login() {
//        btnSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final String username = etUsername.getText().toString();
//                final String password = etPassword.getText().toString();
//
//                pbLogin.setVisibility(View.VISIBLE);
//
//                if (username.isEmpty() || password.isEmpty()){
//                    pbLogin.setVisibility(View.GONE);
//                    Toast.makeText(login_activity.this, "Username & Password Salah !", Toast.LENGTH_SHORT).show();
//                }else {
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            String spUsername = sharedPrefManager.getUsername();
//                            String spPassword = sharedPrefManager.getPassword();
//
//                            Log.d("username", "user" + username);
//                            Log.d("password", "pass" + password);
//
//                            if (username.equals(spUsername) && password.equals(spPassword)){
//                                Intent i = new Intent(login_activity.this, MainActivity.class);
//                                sharedPrefManager.saveIsLogin(true);
//                                finishAffinity();
//                                startActivity(i);
//                            }else{
//                                pbLogin.setVisibility(View.GONE);
//                                Toast.makeText(login_activity.this, "Username & Password Salah !", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }, 3000);
//                }
//            }
//        });
//    }
//
//
//}

public class login_activity extends AppCompatActivity {
    private SharedPrefManager sharedPrefManager;
    EditText username, password;
    Button btnLogin;
    private TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPrefManager = new SharedPrefManager(this);

        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnSignIn);
        tvSignUp = findViewById(R.id.tvSignUp);

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login_activity.this, SignupActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(login_activity.this, "Kolom Username atau Password harap diisi", Toast.LENGTH_LONG).show();
                } else{
                    login();
                }
            }
        });
    }

    public void login(){
        String user = username.getText().toString();
        String pass = password.getText().toString();
        if (user.equals("admin") &&  pass.equals("admin")){
            Toast.makeText(login_activity.this,"Login Admin Successful", Toast.LENGTH_LONG).show();
            sharedPrefManager.saveIsLogin(true);
            sharedPrefManager.saveUsername(user);
            Intent i = new Intent(login_activity.this, ViewDataActivity.class);
            finishAffinity();
            startActivity(i);
        }else {

            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUsername(username.getText().toString());
            loginRequest.setPassword(password.getText().toString());

            Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
            loginResponseCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    if (response.isSuccessful()) {
                        Toast.makeText(login_activity.this, "Login Successful", Toast.LENGTH_LONG).show();
                        LoginResponse loginResponse = response.body();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                sharedPrefManager.saveIsLogin(true);
                                sharedPrefManager.saveId(loginResponse.getId());
                                sharedPrefManager.saveUsername(loginResponse.getUsername());
                                sharedPrefManager.saveEmail(loginResponse.getEmail());
                                sharedPrefManager.savePassword(password.getText().toString());

                                startActivity(new Intent(login_activity.this, MainActivity.class));

                            }
                        }, 700);

                    } else {
                        if(response.isSuccessful() == false){
                            Toast.makeText(login_activity.this, "Login Failed. Check Your Username or Password", Toast.LENGTH_LONG).show();
                        }
                    }
                }


                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(login_activity.this, "Login Failed. Try again. " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }

            });
        }
    }


}