package com.example.dell.orps;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class Recyclerviews extends AppCompatActivity {
    private int Number_List=1;
    MyAdapter adapter;
    int count;
    RecyclerView checkList;
    public String slot_fpno,Book_time,commit_status,checkin,checkout,key,key1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerviews);
        final SharedPreferences sharedPreferences=getSharedPreferences("Keys",0);
        count=sharedPreferences.getAll().size();
            StringRequest json1 = new StringRequest(Request.Method.POST, getResources().getString(R.string.url) + "checkBooking.php", new Response.Listener<String>() {
                @Override
                public void onResponse(final String response) {
                    if (response.trim() != "negative" || response != null) {
                        try {

                            JSONObject jsonobject;
                            jsonobject = new JSONObject(response);
                            slot_fpno = jsonobject.getString("slot_fpno").toString();
                            Book_time = jsonobject.getString("Book_time").toString();
                            commit_status = jsonobject.getString("commit_status").toString();
                            checkin = jsonobject.getString("chkin_time").toString();
                            checkout = jsonobject.getString("chkout_time").toString();
                            MyAdapter myAdapter = new MyAdapter(sharedPreferences.getString("ORPS1", null), Book_time, commit_status, checkin, checkout);
                        } catch (JSONException e) {
                            if (e != null) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        Toast.makeText(Recyclerviews.this, "No response from server", Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    if ((error instanceof TimeoutError || error instanceof NoConnectionError) && error != null) {
                        Toast.makeText(Recyclerviews.this, "Noconnection" + error, Toast.LENGTH_SHORT).show();
                    } else if (error instanceof AuthFailureError && error != null) {
                        Toast.makeText(Recyclerviews.this, "Failureerror", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ServerError && error != null) {
                        Toast.makeText(Recyclerviews.this, "Server error", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof NetworkError && error != null) {
                        Toast.makeText(Recyclerviews.this, "NetworkError", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ParseError && error != null) {
                        Toast.makeText(Recyclerviews.this, "ParseError", Toast.LENGTH_SHORT).show();
                    }
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();

                    map.put("key", sharedPreferences.getString("ORPS1", null));

                    return map;
                }
            };
            MySingleton.getInstance(this).addToRequestQueue(json1);


            checkList = (RecyclerView) findViewById(R.id.checklist);

            LinearLayoutManager mlayoutmanager = new LinearLayoutManager(this);
            checkList.setLayoutManager(mlayoutmanager);
            checkList.setHasFixedSize(true);
            adapter = new MyAdapter(Number_List);
            checkList.setAdapter(adapter);
        }
    }

