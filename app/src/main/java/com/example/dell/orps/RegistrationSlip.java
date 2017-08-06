package com.example.dell.orps;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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
    String reg_no,slot_fpno,Book_time,chkin_time,chkout_time,commit_status;
    TextView info;
    Button button;
    DetailsActivity detailsActivity=new DetailsActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_slip);
        button=(Button)findViewById(R.id.button2);
        info=(TextView)findViewById(R.id.info);
        Log.v("vansh","response");
        ImageView imageView = (ImageView) findViewById(R.id.qrCode);
         stringrequest();
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
            public void onResponse(String response) {

                if (response.trim()!="negative") {
                    try {

                        JSONObject jsonobject;
                        jsonobject = new JSONObject(response);
                        reg_no=jsonobject.getString("reg_no").toString();
                        slot_fpno=jsonobject.getString("slot_fpno").toString();
                        Book_time=jsonobject.getString("Book_time").toString();
                        chkin_time=jsonobject.getString("chkin_time").toString();
                        chkout_time=jsonobject.getString("chkout_time").toString();
                        commit_status=jsonobject.getString("commit_status").toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(RegistrationSlip.this, "No response from server", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(RegistrationSlip.this, "Noconnection"+error, Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(RegistrationSlip.this, "Failureerror", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(RegistrationSlip.this, "Server error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(RegistrationSlip.this, "NetworkError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(RegistrationSlip.this, "ParseError", Toast.LENGTH_SHORT).show();
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("custname",detailsActivity.getname());
                map.put("custnum",detailsActivity.getcontact());
                map.put("custemail",detailsActivity.getemail());
                map.put("vehicleno",detailsActivity.getvehiclenumber());
                map.put("vehicletype",detailsActivity.getvehicletype());
                map.put("vehiclename",detailsActivity.getvehiclename());
                map.put("vehiclecolor",detailsActivity.getvehiclecolor());
                map.put("stationid",detailsActivity.getstationid());
                return map;
            }
        }
                ;
        json1.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(this).addToRequestQueue(json1);
    }
    }

