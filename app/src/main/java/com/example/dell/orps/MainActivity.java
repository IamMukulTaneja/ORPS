package com.example.dell.orps;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
TextView welcome;
    Spinner stationspinner;
    ArrayAdapter<String> adapter;
    List<String> stationlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        stationspinner=(Spinner)findViewById(R.id.stationspinner);
        welcome=(TextView)findViewById(R.id.welcome);
        welcome.setText(R.string.welcome1);
        SharedPreferences sharedPreferences=getSharedPreferences("Mypref",0);
        String name=sharedPreferences.getString("NAME",null);
        welcome.append(name);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.bookfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        stationlist=new ArrayList<String>();
        stationlist.add("GHY");
        stationlist.add("NDLS");
        adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,stationlist);
        stationspinner.setAdapter(adapter);
        stationspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i)=="GHY")
                {
                    Toast.makeText(MainActivity.this, "yo "+adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
