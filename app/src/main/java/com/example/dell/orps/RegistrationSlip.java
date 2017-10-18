package com.example.dell.orps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationSlip extends AppCompatActivity {
    String vehnumber,vehname,vehcolor,vehtype;
    String name,email,contact,selectedstation;
    TextView customer_nameTextView,veh_number_text_view,regNoTextView,book_timeTextView,slot_number_text_view,reciept,railwayparking,address,booking_charges;
    TextView custName,vehNumber,regNo,BookTime,SlotNumber,bookingCharges,colon1,colon2,colon3,colon4,colon5,colon6;
    ProgressBar mProgressBar;
    Button checkin_button,cancel_button;
    String  reg_no,slot_fpno,Book_time,commit_status;
   SharedPreference sharedPreference=new SharedPreference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_slip);
        custName = (TextView) findViewById(R.id.customer_name_text);
        vehNumber = (TextView) findViewById(R.id.veh_number_text);
       checkin_button=(Button)findViewById(R.id.checkin_button);
        cancel_button=(Button)findViewById(R.id.cancel_button);
        regNo = (TextView) findViewById(R.id.Reg_no_text);
        BookTime = (TextView) findViewById(R.id.book_time_text);
        SlotNumber = (TextView) findViewById(R.id.slot_no_text);
        bookingCharges = (TextView) findViewById(R.id.book_charge_text);
        colon1 = (TextView) findViewById(R.id.colon1);
        colon2 = (TextView) findViewById(R.id.colon2);
        colon3 = (TextView) findViewById(R.id.colon3);
        colon4 = (TextView) findViewById(R.id.colon4);
        colon5 = (TextView) findViewById(R.id.colon5);
        colon6 = (TextView) findViewById(R.id.colon6);
        customer_nameTextView = (TextView) findViewById(R.id.customer_name);
        regNoTextView = (TextView) findViewById(R.id.Reg_no);
        veh_number_text_view = (TextView) findViewById(R.id.vehicle_number);
        reciept = (TextView) findViewById(R.id.reciept);
        address = (TextView) findViewById(R.id.address);
        railwayparking = (TextView) findViewById(R.id.railway_parking);
        booking_charges = (TextView) findViewById(R.id.booking_charges);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
        book_timeTextView = (TextView) findViewById(R.id.book_Time);
        slot_number_text_view = (TextView) findViewById(R.id.slot_number);
        sharedPreference.getContext(this);
        Intent y = getIntent();
        vehnumber = y.getStringExtra("vn");

        vehname = y.getStringExtra("vname");
        vehcolor = y.getStringExtra("vc");
        vehtype = y.getStringExtra("vtype");
        name = y.getStringExtra("name");
        email = y.getStringExtra("email");
        contact = y.getStringExtra("contact");
        customer_nameTextView.setText(name);
        veh_number_text_view.setText(vehnumber);
cancel_button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(RegistrationSlip.this,Cancel.class);
        startActivity(i);
    }
});
        checkin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegistrationSlip.this,Checkin.class);
                startActivity(i);
            }
        });
        stringrequest();

        booking_charges.setText("INR 30");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

public void stringrequest()
{

    StringRequest json1=new StringRequest(Request.Method.POST,getResources().getString(R.string.url)+"getBooking.php", new Response.Listener<String>() {
        @Override
        public void onResponse(final String response) {
            if (response.trim()!="negative" || response!= null) {
                try {
                    JSONObject jsonobject;
                    jsonobject = new JSONObject(response);
                    reg_no=jsonobject.getString("reg_no").toString();
                    slot_fpno=jsonobject.getString("slot_fpno").toString();
                    Book_time=jsonobject.getString("Book_time").toString();
                    commit_status=jsonobject.getString("commit_status").toString();
                    sharedPreference.getInfo(reg_no,name,slot_fpno,Book_time,"INR 30",vehnumber);
                    slot_number_text_view.setText(slot_fpno);
                    regNoTextView.setText(reg_no);
                    book_timeTextView.setText(Book_time);
                    mProgressBar.setVisibility(View.INVISIBLE);
                    railwayparking.setVisibility(View.VISIBLE);
                    address.setVisibility(View.VISIBLE);
                    reciept.setVisibility(View.VISIBLE);
                    customer_nameTextView.setVisibility(View.VISIBLE);
                    book_timeTextView.setVisibility(View.VISIBLE);
                    regNoTextView.setVisibility(View.VISIBLE);
                    veh_number_text_view.setVisibility(View.VISIBLE);
                    booking_charges.setVisibility(View.VISIBLE);
                    slot_number_text_view.setVisibility(View.VISIBLE);
                    custName.setVisibility(View.VISIBLE);
                    vehNumber.setVisibility(View.VISIBLE);
                    regNo.setVisibility(View.VISIBLE);
                    SlotNumber.setVisibility(View.VISIBLE);
                    bookingCharges.setVisibility(View.VISIBLE);
                    BookTime.setVisibility(View.VISIBLE);
                    checkin_button.setVisibility(View.VISIBLE);
                    cancel_button.setVisibility(View.VISIBLE);
                    RecieptShower recieptShower=new RecieptShower();
                    recieptShower.setCustname(name);
                    recieptShower.setVehNo(vehnumber);
                    recieptShower.setRegNo(reg_no);
                    recieptShower.setBookTime(Book_time);
                    recieptShower.setSlotNo(slot_fpno);
                    recieptShower.setBookCharge("INR 30");
                } catch (JSONException e) {
                    if(e!=null){
                        e.printStackTrace();
                    }}
            }
            else
            {
                Toast.makeText(RegistrationSlip.this, "No response from server", Toast.LENGTH_SHORT).show();
            }

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            if ((error instanceof TimeoutError || error instanceof NoConnectionError) &&  error!=null) {
                reciept.setText("PLEASE CHECK YOUR INTERNET CONNECTION AND TRY AGAIN !");
                mProgressBar.setVisibility(View.INVISIBLE);
                reciept.setVisibility(View.VISIBLE);
            } else if (error instanceof AuthFailureError &&  error!=null) {
                reciept.setText("ERROR FROM OUR SIDE, WE ARE WORKING ON IT,PLEASE TRY AGAIN LATER !");
                reciept.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
            } else if (error instanceof ServerError &&  error!=null) {
                reciept.setText("ERROR FROM OUR SIDE, WE ARE WORKING ON IT,PLEASE TRY AGAIN LATER !");
                reciept.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
            } else if (error instanceof NetworkError &&  error!=null) {
                reciept.setText("ERROR FROM OUR SIDE, WE ARE WORKING ON IT,PLEASE TRY AGAIN LATER !");
                reciept.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
            } else if (error instanceof ParseError &&  error!=null) {

            }
        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> map=new HashMap<>();
            Log.v("Vansh","res"+vehnumber+vehcolor+vehtype+name+email+contact+vehname+selectedstation);
            map.put("custname",name);
            map.put("custnum",contact);
            map.put("custemail",email);
            map.put("vehicleno",vehnumber);
            map.put("vehicletype",vehtype);
            map.put("vehiclename",vehname);
            map.put("vehiclecolor",vehcolor);
            map.put("stationid","ghy");
            return map;
        }
    }
            ;
    MySingleton.getInstance(this).addToRequestQueue(json1);
}

    }

