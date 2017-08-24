package com.example.dell.orps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CurrentBooking extends AppCompatActivity {
String regNo,bookTime;
    LinearLayout linearLayout;
    TextView currentBookingtext,noBookingtext;
    Button checkin,checkout,cancel,receipt;
    TextView book_time,reg_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_booking);
        checkin=(Button)findViewById(R.id.checkin_button);
        checkout=(Button)findViewById(R.id.checkout_button);
        cancel=(Button)findViewById(R.id.cancel_button);
       receipt=(Button)findViewById(R.id.e_reciept);
        linearLayout=(LinearLayout)findViewById(R.id.linearLayout);
        currentBookingtext=(TextView)findViewById(R.id.currentBooking);
        noBookingtext=(TextView)findViewById(R.id.noBooking);
        reg_no=(TextView)findViewById(R.id.reg_no1);
        book_time=(TextView)findViewById(R.id.book_time1);
        RecieptShower recieptShower=new RecieptShower();
        bookTime=recieptShower.getBookTime();
        regNo= recieptShower.getRegNo();
        reg_no.setText(regNo);
        book_time.setText(bookTime);

if(regNo==null || regNo=="")
{
    noBookingtext.setVisibility(View.VISIBLE);
}
else
{
    linearLayout.setVisibility(View.VISIBLE);
    currentBookingtext.setVisibility(View.VISIBLE);
}
checkin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(CurrentBooking.this,Checkin.class);
        startActivity(i);
    }
});
       checkout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(CurrentBooking.this,Checkout.class);
               startActivity(i);

           }
       });
cancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(CurrentBooking.this,Cancel.class);
        startActivity(i);

    }
});
        receipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CurrentBooking.this,RecieptShower.class);
                startActivity(i);

            }
        });
    }


}
