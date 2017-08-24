package com.example.dell.orps;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
TextView welcome;
    Button findparking,check;
    Spinner stationspinner;
    ArrayAdapter<String> adapter;
    List<String> stationlist;
    String selectedstation;
    String TAG="mainactivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stationspinner=(Spinner)findViewById(R.id.stationspinner);
        welcome=(TextView)findViewById(R.id.welcome);
        SharedPreferences sharedPreferences=getSharedPreferences("Mypref",0);
        String name=sharedPreferences.getString("NAME",null);
        welcome.append(name);
        stationlist=new ArrayList<String>();
        stationlist.add("Guwahati");
        stationlist.add("New Delhi");
        stationlist.add("Howrah");
        stationlist.add("Chhatrapati Shivaji");
        stationlist.add("Chennai Central");
        adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,stationlist);
        stationspinner.setAdapter(adapter);
        stationspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).toString() == "Guwahati")
                {
                    selectedstation="ghy";
                }
                else if(adapterView.getItemAtPosition(i).toString() == "New Delhi")
                {
                    selectedstation="ndls";
                }
                else if(adapterView.getItemAtPosition(i).toString() == "Howrah")
                {
                    selectedstation="hwh";
                }
                else if(adapterView.getItemAtPosition(i).toString() == "Chhatrapati Shivaji")
                {
                    selectedstation="cstm";
                }
                else if(adapterView.getItemAtPosition(i).toString() == "Chennai Central")
                {
                    selectedstation="mas";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        findparking=(Button)findViewById(R.id.findparking);
        findparking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent post=new Intent(MainActivity.this,ParkingDetails.class);
                post.putExtra(Intent.EXTRA_TEXT,selectedstation);
                startActivity(post);

            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.myFAB);
fab.setOnClickListener(new View.OnClickListener() {@Override
public void onClick(View view) {
// Click action
Intent intent = new Intent(MainActivity.this, PersonalDetails.class);
    intent.putExtra("station",selectedstation);
startActivity(intent);
}
});
 check=(Button)findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,CurrentBooking.class);
                startActivity(i);
            }
        });
    }

}
