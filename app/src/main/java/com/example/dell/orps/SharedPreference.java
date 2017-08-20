package com.example.dell.orps;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mukul on 20-08-2017.
 */

public class SharedPreference  {
    String key;
    Context context;
    int count;
    int i=0;
    public void getContext(Context mContext) {
    context=mContext;

    }
    public void getKey(String mKey){

        key=mKey;
        setSharedPreferences();
    }
    public void setSharedPreferences() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Keys", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        count=sharedPreferences.getAll().size();
        i=count+1;
        editor.putString("ORPS"+i,key);
        editor.apply();
    }
}
