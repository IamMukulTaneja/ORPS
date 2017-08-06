package com.example.dell.orps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class DetailsActivity extends AppCompatActivity {
    String selectedstation;
    String name,contact,email,vehnumber,vehtype,vehcolor,vehname;
    final DetailsFragment detailsFragment=new DetailsFragment();
    final CarDetailsFragment carDetailsFragment=new CarDetailsFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final FragmentManager fragmentManager=getSupportFragmentManager();
        Intent i=getIntent();
        selectedstation=i.getStringExtra("station");
        fragmentManager.beginTransaction().add(R.id.headcontainer,detailsFragment).commit();
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        name=detailsFragment.namepost;
        contact=detailsFragment.contactpost;
        email=detailsFragment.emailpost;
        vehnumber=carDetailsFragment.vehiclenumberpost;
        vehtype=carDetailsFragment.vehicletypepost;
        vehcolor=carDetailsFragment.vehiclecolorpost;
        Log.v("vansh","res"+name);
    }

    public String getname()
    {
        return name;
    }
    public String getcontact()
    {
        return contact;
    }
    public String getemail()
    {
        return email;
    }
    public String getvehiclenumber()
    {
        return vehnumber;
    }
    public String getvehicletype()
    {
        return vehtype;
    }
    public String getvehiclename()
    {
        return vehname;
    }
    public String getvehiclecolor()
    {
        return vehcolor;
    }
    public String getstationid()
    {
        return selectedstation;
    }
    }

