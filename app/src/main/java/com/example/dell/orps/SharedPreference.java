package com.example.dell.orps;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mukul on 20-08-2017.
 */

public class SharedPreference  {
    String key,name,slot,book_time,book_charge,vehnumber;
    Context context;
    public void getContext(Context mContext) {
    context=mContext;

    }
    public void getInfo(String mKey,String mName,String mSlot,String mbook_time,String mbook_charge,String mvehNumber){
        key=mKey;
        name=mName;
        slot=mSlot;
        book_time=mbook_time;
        book_charge=mbook_charge;
        vehnumber=mvehNumber;
        setSharedPreferences();
    }
    public void setSharedPreferences() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Info", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key",key);
        editor.putString("name",name);
        editor.putString("slot",slot);
        editor.putString("booktime",book_time);
        editor.putString("bookcharge",book_charge);
        editor.putString("vehnumber",vehnumber);
        editor.apply();
    }
}
