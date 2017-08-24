package com.example.dell.orps;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Checkin extends AppCompatActivity {
    ImageView imageView;
    Bitmap bitmap;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        button=(Button)findViewById(R.id.home);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Checkin.this,MainActivity.class);
                startActivity(i);
            }
        });
        imageView = (ImageView) findViewById(R.id.qrCode);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {

            BitMatrix bitMatrix = multiFormatWriter.encode("key", BarcodeFormat.QR_CODE,800,800);

            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();

            bitmap = barcodeEncoder.createBitmap(bitMatrix);

        } catch (WriterException e) {

            e.printStackTrace();

        }
        imageView.setImageBitmap(bitmap);
    }
    }

