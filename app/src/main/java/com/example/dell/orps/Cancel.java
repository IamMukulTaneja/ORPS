package com.example.dell.orps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

import java.util.HashMap;
import java.util.Map;

public class Cancel extends AppCompatActivity {
String key;
    String res;
    RelativeLayout relativeLayout;
    TextView cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);
        RecieptShower recieptShower=new RecieptShower();
        cancel=(TextView)findViewById(R.id.cancel);
        relativeLayout=(RelativeLayout)findViewById(R.id.relativeLayout);
        key=recieptShower.getRegNo();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, getResources().getString(R.string.url)+"cancelation.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("positive")) {
                    relativeLayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    cancel.setText("Try again !");
                    cancel.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    cancel.setText("PLEASE CHECK YOUR INTERNET CONNECTION AND TRY AGAIN !");
                    cancel.setVisibility(View.VISIBLE);
                } else if (error instanceof AuthFailureError) {
                    cancel.setText("ERROR FROM OUR SIDE, WE ARE WORKING ON IT,PLEASE TRY AGAIN LATER !");
                    cancel.setVisibility(View.VISIBLE);
                } else if (error instanceof ServerError) {
                   cancel.setText("ERROR FROM OUR SIDE, WE ARE WORKING ON IT,PLEASE TRY AGAIN LATER !");
                    cancel.setVisibility(View.VISIBLE);
                } else if (error instanceof NetworkError) {
                    cancel.setText("ERROR FROM OUR SIDE, WE ARE WORKING ON IT,PLEASE TRY AGAIN LATER !");

                } else if (error instanceof ParseError) {
                    cancel.setText("ERROR FROM OUR SIDE, WE ARE WORKING ON IT,PLEASE TRY AGAIN LATER !");
                    cancel.setVisibility(View.VISIBLE);


                }

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> hashmap=new HashMap<>();
                hashmap.put("key",key);
                return  hashmap;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
