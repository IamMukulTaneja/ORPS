package com.example.dell.orps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ParkingDetails extends AppCompatActivity {
    String selectedstation;
    TextView details;
    String station_id,station_name,station_class,tot_2w_park,avail_2w_park,occ_2w_park,res_2w_park;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_details);
        Intent get = getIntent();
        details = (TextView) findViewById(R.id.details);
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
                    details.setText(station_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                details.setText("NAhi HUA"+error);
            }
        });
        queue.add(json);
        json.setRetryPolicy(new DefaultRetryPolicy(
               3000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
}