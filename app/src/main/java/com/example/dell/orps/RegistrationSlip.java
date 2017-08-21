package com.example.dell.orps;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationSlip extends AppCompatActivity {
Bitmap bitmap;
    String vehnumber,vehname,vehcolor,vehtype;
    String name,email,contact,selectedstation;
    TextView customer_nameTextView,veh_number_text_view,regNoTextView,book_timeTextView,slot_number_text_view,reciept,railwayparking,address,booking_charges;
    Button home;
    ProgressBar mProgressBar;
    String  reg_no,slot_fpno,Book_time,commit_status;
   SharedPreference sharedPreference=new SharedPreference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_slip);
        //home=(Button)findViewById(R.id.button2);
        customer_nameTextView=(TextView)findViewById(R.id.customer_name);
        regNoTextView=(TextView)findViewById(R.id.Reg_no);
        veh_number_text_view=(TextView)findViewById(R.id.vehicle_number);
        reciept=(TextView)findViewById(R.id.reciept);
        address=(TextView)findViewById(R.id.address);
        railwayparking=(TextView)findViewById(R.id.railway_parking);
        booking_charges=(TextView)findViewById(R.id.booking_charges);
        mProgressBar=(ProgressBar)findViewById(R.id.progressBar1);
       book_timeTextView=(TextView)findViewById(R.id.book_Time);
        slot_number_text_view=(TextView)findViewById(R.id.slot_number);
        sharedPreference.getContext(this);
        Intent y=getIntent();
        vehnumber=y.getStringExtra("vn");
        vehname=y.getStringExtra("vname");
        vehcolor=y.getStringExtra("vc");
        vehtype=y.getStringExtra("vtype");
        name=y.getStringExtra("name");
        email=y.getStringExtra("email");
        contact=y.getStringExtra("contact");
        selectedstation=y.getStringExtra("station");
customer_nameTextView.setText("Customer Name :                    "+name);
        veh_number_text_view.setText("Vehicle Number :                    "+vehnumber);
        stringrequest();
       ImageView imageView = (ImageView) findViewById(R.id.qrCode);
       MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {

            BitMatrix bitMatrix = multiFormatWriter.encode("key", BarcodeFormat.QR_CODE,600,600);

           BarcodeEncoder barcodeEncoder = new BarcodeEncoder();

           bitmap = barcodeEncoder.createBitmap(bitMatrix);

        } catch (WriterException e) {

            e.printStackTrace();

        }
        imageView.setImageBitmap(bitmap);
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
                    sharedPreference.getKey(reg_no);
                    slot_number_text_view.setText("Slot Number :                    "+slot_fpno);
                    regNoTextView.setText("Registration Key :                    "+reg_no);
                    book_timeTextView.setText("Book Time :                    "+Book_time);
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
                Toast.makeText(RegistrationSlip.this, "Noconnection"+error, Toast.LENGTH_SHORT).show();
            } else if (error instanceof AuthFailureError &&  error!=null) {
                Toast.makeText(RegistrationSlip.this, "Failureerror", Toast.LENGTH_SHORT).show();
            } else if (error instanceof ServerError &&  error!=null) {
                Toast.makeText(RegistrationSlip.this, "Server error", Toast.LENGTH_SHORT).show();
            } else if (error instanceof NetworkError &&  error!=null) {
                Toast.makeText(RegistrationSlip.this, "NetworkError", Toast.LENGTH_SHORT).show();
            } else if (error instanceof ParseError &&  error!=null) {
                Toast.makeText(RegistrationSlip.this, "ParseError", Toast.LENGTH_SHORT).show();
            }
        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> map=new HashMap<>();
            map.put("custname",name);
            map.put("custnum",contact);
            map.put("custemail",email);
            map.put("vehicleno",vehnumber);
            map.put("vehicletype",vehtype);
            map.put("vehiclename",vehname);
            map.put("vehiclecolor",vehcolor);
            map.put("stationid",selectedstation);
            return map;
        }
    }
            ;
    MySingleton.getInstance(this).addToRequestQueue(json1);
}

    }

