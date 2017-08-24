package com.example.dell.orps;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class RecieptShower extends AppCompatActivity {
    TextView customer_nameTextView,veh_number_text_view,regNoTextView,book_timeTextView,slot_number_text_view,booking_charges_text_view;
static String regNo,vehNo,custName,bookTime,slotNo,bookCharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciept_shower);
        SharedPreferences sharedPreferences=getSharedPreferences("Info",0);
        customer_nameTextView=(TextView)findViewById(R.id.customer_name);
        veh_number_text_view=(TextView)findViewById(R.id.vehicle_number);
        regNoTextView=(TextView)findViewById(R.id.Reg_no);
        book_timeTextView=(TextView)findViewById(R.id.book_Time);
        slot_number_text_view=(TextView)findViewById(R.id.slot_number);
        booking_charges_text_view=(TextView)findViewById(R.id.booking_charges);
        regNoTextView.setText(sharedPreferences.getString("key",null));
        veh_number_text_view.setText(sharedPreferences.getString("vehnumber",null));
        customer_nameTextView.setText(sharedPreferences.getString("name",null));
        book_timeTextView.setText(sharedPreferences.getString("booktime",null));
        slot_number_text_view.setText(sharedPreferences.getString("slot",null));
        booking_charges_text_view.setText(sharedPreferences.getString("bookcharge",null));


    }
    public void setRegNo(String regNo)
    {
        this.regNo=regNo;


    }
    public void setVehNo(String vehNo){
        this.vehNo=vehNo;

    }
    public void setCustname(String custName)
    {
        this.custName=custName;

    }
    public void setBookTime(String bookTime)
    {
        this.bookTime=bookTime;

    }
    public void setSlotNo(String slotNo)
    {
        this.slotNo=slotNo;

    }
    public void setBookCharge(String bookCharge)
    {
        this.bookCharge=bookCharge;

    }
    public String getRegNo()
    {
        return regNo;
    }
    public String getBookTime()
    {
        return bookTime;
    }

}
