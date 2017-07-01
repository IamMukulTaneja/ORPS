package com.example.dell.orps;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcome=(TextView)findViewById(R.id.welcome);
        welcome.setText(R.string.welcome1);
        SharedPreferences sharedPreferences=getSharedPreferences("Mypref",0);
        String name=sharedPreferences.getString("NAME",null);
        welcome.append(name);

    }
}
