package com.example.dell.orps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersonalDetails extends AppCompatActivity {
    public Button next;
    EditText name;
    EditText contact;
    EditText email;
    public String namepost,contactpost,emailpost,selectedstation;
    Context context;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        name=(EditText)findViewById(R.id.name);
        contact=(EditText)findViewById(R.id.contact);
        email=(EditText)findViewById(R.id.email);
        next=(Button)findViewById(R.id.Next);
        SharedPreferences sharedPreferences=getSharedPreferences("Mypref",0);
        String name1=sharedPreferences.getString("NAME",null);
        name.setText(name1);
        String contact1=sharedPreferences.getString("CONTACT",null);
        contact.setText(contact1);
        String email1=sharedPreferences.getString("EMAIL",null);
        email.setText(email1);
Intent i1=getIntent();
        selectedstation=i1.getStringExtra("station");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namepost=name.getText().toString();
                contactpost=contact.getText().toString();
                emailpost=email.getText().toString();
                Intent intent=new Intent(PersonalDetails.this,VehicleDetails.class);
                intent.putExtra("name",namepost);
                intent.putExtra("contact",contactpost);
                intent.putExtra("email",emailpost);
                intent.putExtra("station",selectedstation);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent upIntent = new Intent(this,ParkingDetails.class);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is not part of the application's task, so
                    // create a new task
                    // with a synthesized back stack.
                    TaskStackBuilder
                            .from(this)
                            .addNextIntent(new Intent(this, MainActivity.class))
                            .addNextIntent(upIntent).startActivities();
                    finish();
                } else {
                    // This activity is part of the application's task, so simply
                    // navigate up to the hierarchical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }

        }
        return true;
    }
}
