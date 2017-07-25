package com.example.dell.orps;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
EditText name,contact,email,vehiclenumber,vehiclecolor,vehiclename;
    Button confirmandpay;
    Spinner vehicletype;
    List<String> vehicletypelist;
    SpinnerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        name=(EditText)findViewById(R.id.name);
        contact=(EditText)findViewById(R.id.contact);
        email=(EditText)findViewById(R.id.email);
        vehicletype=(Spinner)findViewById(R.id.vehicletypespinner);
        vehiclenumber=(EditText)findViewById(R.id.vehiclenumber);
        vehiclecolor=(EditText)findViewById(R.id.vehiclecolor);
        vehiclename=(EditText)findViewById(R.id.vehiclename);
        SharedPreferences sharedPreferences=getSharedPreferences("Mypref",0);
        String name1=sharedPreferences.getString("NAME",null);
        name.setText(name1);
        String contact1=sharedPreferences.getString("CONTACT",null);
        contact.setText(contact1);
        String email1=sharedPreferences.getString("EMAIL",null);
        email.setText(email1);
        confirmandpay=(Button)findViewById(R.id.confirmbooking);
        confirmandpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().trim().equals(""))
                {

                }
            }
        });
        vehicletypelist=new ArrayList<String>();
        vehicletypelist.add("Two Wheeler");
        vehicletypelist.add("Four Wheeler");
        adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,vehicletypelist);
        vehicletype.setAdapter(adapter);
        vehicletype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
