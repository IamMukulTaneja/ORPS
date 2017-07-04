package com.example.dell.orps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ParkingDetails extends AppCompatActivity {
    ArrayAdapter<String> stationadapter;
    ArrayAdapter<String> TwoWheeleradapter;
    ArrayAdapter<String> FourWheeleradapter;
    List<String> stationdetails,twowheelerlist,fourwheelerlist;
    String selectedstation;
    ListView station_details,two_wheeler,four_wheeler;
    String station_id,station_name,station_class,tot_2w_park,avail_2w_park,tot_4w_park,avail_4w_park;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_details);
        Intent get = getIntent();
        station_details = (ListView)findViewById(R.id.stationdetails);
        two_wheeler = (ListView)findViewById(R.id.Two_Wheeler);
        four_wheeler = (ListView)findViewById(R.id.Four_Wheeler);
        if (get.hasExtra(Intent.EXTRA_TEXT)) {
            selectedstation = get.getStringExtra(Intent.EXTRA_TEXT);
        }
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.43.143/OPRS-server-master/getStationParkingStatus.php";
        JsonObjectRequest json = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    station_id=response.getString("station_id");
                    station_name=response.getString("station_name");
                    station_class=response.getString("station_class");
                    tot_2w_park=response.getString("tot_2w_park");
                    avail_2w_park=response.getString("avail_2w_park");
                    tot_4w_park=response.getString("tot_4w_park");
                    avail_4w_park=response.getString("avail_4w_park");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(json);
        json.setRetryPolicy(new DefaultRetryPolicy(
               3000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        stationdetails= new ArrayList<>();
        stationdetails.add("Station ID : "+station_id);
        stationdetails.add("Station Name : "+station_name);
        stationdetails.add("Station Class : "+station_class);
        twowheelerlist= new ArrayList<>();
        twowheelerlist.add("Total Parking : "+tot_2w_park);
        twowheelerlist.add("Available Parking : "+avail_2w_park);
        fourwheelerlist=new ArrayList<>();
        fourwheelerlist.add("Total Parking : "+tot_4w_park);
        fourwheelerlist.add("Available Parking : "+avail_4w_park);
        stationadapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,stationdetails);
        station_details.setAdapter(stationadapter);
        TwoWheeleradapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,twowheelerlist);
        two_wheeler.setAdapter(TwoWheeleradapter);
        FourWheeleradapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,fourwheelerlist);
        four_wheeler.setAdapter(FourWheeleradapter);
    }

}