package com.example.dell.orps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {
EditText name,contact;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        name=(EditText)findViewById(R.id.name);
        contact=(EditText)findViewById(R.id.phone);
        signup=(Button)findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences=getSharedPreferences("Mypref",0);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("NAME",name.getText().toString().trim());
                editor.putString("CONTACT",contact.getText().toString().trim());
                editor.apply();
                Intent i=new Intent(SignInActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
