package com.example.fandyaditya.silomba;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Fandy Aditya on 5/11/2017.
 */

public class Session {

    private Context context;
    private SharedPreferences sharedPreferences;

    public Session(Context context) {
        this.context = context;
        this.sharedPreferences = this.context.getSharedPreferences("myPreferences",Context.MODE_PRIVATE);
    }

    public void editPreferences(String nrp){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nrp",nrp);
        editor.apply();
    }
    public void removePreferences(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
    public String getPreferences() {
        String returnVal;
        returnVal = sharedPreferences.getString("nrp", null);
        return returnVal;
    }
}
