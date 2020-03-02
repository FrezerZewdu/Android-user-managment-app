package com.example.finaltry;

import android.content.Context;
import android.content.SharedPreferences;

public class Shared {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;


    public Shared(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("com.example.finaltry.Shared_login", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }
    public void wLogInStatus(boolean b){
        editor.putBoolean("loggedInmode",b);
        editor.commit();
    }
    public boolean rLogInStatus(){
        return prefs.getBoolean("loggedInMode",false);
    }
}
