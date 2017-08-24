package com.example.dell.orps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
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

public class VehicleDetails extends AppCompatActivity {
    List<String> vehicletypelist;
    SpinnerAdapter adapter;
    EditText vehiclenumber;
    EditText vehiclecolor;
    EditText vehiclename;
    Context context;
    Button confirmandpay;
    Button previous;
  public String vehiclenumberpost,vehicletypepost,vehiclenamepost,vehiclecolorpost,name1,email,contact1,selectedstation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);
        final Spinner vehicletype=(Spinner)findViewById(R.id.vehicletypespinner);
        vehiclenumber=(EditText)findViewById(R.id.vehiclenumber);
        vehiclecolor=(EditText)findViewById(R.id.vehiclecolor);
        vehiclename=(EditText)findViewById(R.id.vehiclename);
        confirmandpay=(Button)findViewById(R.id.confirmandpay);
        previous=(Button)findViewById(R.id.previous);
        vehicletypelist=new ArrayList<String>();
        vehicletypelist.add("Two Wheeler");
        vehicletypelist.add("Four Wheeler");
Intent i=getIntent();
        name1=i.getStringExtra("name");
        email=i.getStringExtra("email");
        contact1=i.getStringExtra("contact");
        selectedstation=i.getStringExtra("station");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        adapter=new ArrayAdapter<String>(this,R.layout.spinner_item,vehicletypelist);
        vehicletype.setAdapter(adapter);
        vehicletype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).toString()=="Two Wheeler")
                {
                    vehicletypepost="2w";
                }
                else
                {
                    vehicletypepost="4w";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        confirmandpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehiclenumberpost=vehiclenumber.getText().toString();
                vehiclecolorpost=vehiclecolor.getText().toString();
                vehiclenamepost=vehiclename.getText().toString();
                SharedPreferences sharedPreference=getSharedPreferences("Mypref",0);
                SharedPreferences.Editor editor=sharedPreference.edit();
                editor.putString("vehicleNumber",vehiclenumberpost);
                editor.putString("vehicleColor",vehiclecolorpost);
                editor.putString("vehicleName",vehiclenamepost);
                editor.apply();
                Bundle bundle=new Bundle();
                bundle.putString("vehnumber",vehiclenumberpost);
                bundle.putString("vehcolor",vehiclecolorpost);
                bundle.putString("vehname",vehiclenamepost);
                bundle.putString("vehtype",vehicletypepost);
                bundle.putString("name",name1);
                bundle.putString("email",email);
                bundle.putString("contact",contact1);
                bundle.putString("station",selectedstation);
                BookingDialogueFragment bookingDialogueFragment=new BookingDialogueFragment();
                bookingDialogueFragment.setArguments(bundle);
                bookingDialogueFragment.show(getSupportFragmentManager(), "NoticeDialogFragment");

            }
        });
        previous.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VehicleDetails.this,PersonalDetails.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences1=getSharedPreferences("Mypref",0);
        vehiclenumber.setText(sharedPreferences1.getString("vehicleNumber",null));
        vehiclecolor.setText(sharedPreferences1.getString("vehicleColor",null));
        vehiclename.setText(sharedPreferences1.getString("vehicleName",null));

    }
}
