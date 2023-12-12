package com.example.cw;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class QRPage extends AppCompatActivity {
    Button btnCamera;
    ImageButton btnBack, btnQRGenerator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrpage);
        btnBack = findViewById(R.id.back);
        btnQRGenerator = findViewById(R.id.qrGenerator);
        btnCamera = findViewById(R.id.openCamera);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LogIn.class);
                startActivity(intent);
                finish();
            }
        });

        btnQRGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QRGenerator.class);
                startActivity(intent);
                finish();
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQrCode();
            }
        });


    }

    private void scanQrCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Scanning QR Code"); // change this message
        options.setBeepEnabled(true);
        options.setOrientationLocked(true); //optional
        options.setCaptureActivity(CameraCapture.class);
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents()!=null){
            AlertDialog.Builder builder = new AlertDialog.Builder(QRPage.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }
    });
}