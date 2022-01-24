package com.example.visitapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SharedPrefManager(Context context ){
        sharedPreferences = context.getSharedPreferences("Latihan shared_pref", context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveId(String value){
        editor.putString("sp_id", value);
        editor.commit();
    }

    public String getId(){
        return sharedPreferences.getString("sp_id", "");
    }

    public void saveUsername(String value){
        editor.putString("sp_username", value);
        editor.commit();
    }

    public String getUsername(){
        return sharedPreferences.getString("sp_username", "");
    }

    public void saveEmail(String value){
        editor.putString("sp_email", value);
        editor.commit();
    }

    public String getEmail(){
        return sharedPreferences.getString("sp_email", "");
    }

    public void savePassword(String value){
        editor.putString("sp_password", value);
        editor.commit();
    }

    public String getPassword(){
        return sharedPreferences.getString("sp_password", "");
    }

    public String getFoto(){
        return sharedPreferences.getString("sp_foto", "profil");
    }

    public void saveIsLogin(Boolean value){
        editor.putBoolean("sp_islogin", value);
        editor.commit();
    }

    public boolean getIsLogin(){
        return sharedPreferences.getBoolean("sp_islogin", false);
    }
}
