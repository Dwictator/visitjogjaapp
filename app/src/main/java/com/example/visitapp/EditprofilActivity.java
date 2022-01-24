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

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.visitapp.ui.ViewDataActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class EditprofilActivity extends AppCompatActivity {

    EditText username, email, password;
    Button simpan;

    RecyclerView myRecycleview, searchRecyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofil);

        SharedPrefManager sharedPrefManager = new SharedPrefManager(EditprofilActivity.this);

        username = findViewById(R.id.etUsername);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        simpan = findViewById(R.id.btnSimpan);

        username.setText(sharedPrefManager.getUsername());
        email.setText(sharedPrefManager.getEmail());
        password.setText(sharedPrefManager.getPassword());


        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(EditprofilActivity.this, "Semua kolom harap diisi", Toast.LENGTH_LONG).show();
                } else{
                    updatedata();
                }
            }
        });


    }

    public void updatedata(){

        SharedPrefManager sharedPrefManager = new SharedPrefManager(EditprofilActivity.this);
        String sharedprefid = sharedPrefManager.getId();

        UpdateRequest updateRequest = new UpdateRequest(sharedprefid);
        updateRequest.setEmail(email.getText().toString());
        updateRequest.setUsername(username.getText().toString());
        updateRequest.setPassword(password.getText().toString());

        Call<UpdateResponse> updateResponseCall = ApiClient.getUserService().userUpdate(sharedprefid, updateRequest);
        updateResponseCall.enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(EditprofilActivity.this,"Update Successful", Toast.LENGTH_LONG).show();
                    UpdateResponse signupResponse =  response.body();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 700);
                }else{
                    Toast.makeText(EditprofilActivity.this,"Sign up gagal. Coba lagi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                Toast.makeText(EditprofilActivity.this,"Throwable"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}