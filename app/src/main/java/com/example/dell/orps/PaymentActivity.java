package com.example.dell.orps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class PaymentActivity extends AppCompatActivity {
TextView paytm,railway,card,other;
    String vn,vc,vname,vtype,name,email,contact,selectedstation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        paytm=(TextView)findViewById(R.id.paytm);
        railway=(TextView)findViewById(R.id.railway_wallet);
        card=(TextView)findViewById(R.id.card);
        other=(TextView)findViewById(R.id.anyother);
        Intent i=getIntent();
        vn=i.getStringExtra("vehiclenumber");
        vc=i.getStringExtra("vehiclecolor");
        vname=i.getStringExtra("vehiclename");
        vtype=i.getStringExtra("vehicletype");
        name=i.getStringExtra("name");
        email=i.getStringExtra("email");
        contact=i.getStringExtra("contact");
        selectedstation=i.getStringExtra("station");
        paytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PaymentActivity.this,RegistrationSlip.class);
                intent.putExtra("vn",vn);
                intent.putExtra("vc",vc);
                intent.putExtra("vname",vname);
                intent.putExtra("vtype",vtype);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
                intent.putExtra("contact",contact);
                intent.putExtra("station",selectedstation);
                startActivity(intent);
            }
        });
    }
}
