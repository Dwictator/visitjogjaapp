package com.example.visitapp;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateRequest extends AppCompatActivity {

    SharedPrefManager sharedPrefManager = new SharedPrefManager(UpdateRequest.this);

    private String _id ;
    private String email;
    private String username;
    private String password;

    public String getId(){
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UpdateRequest(String _id){
        this._id = _id;
    }
}
