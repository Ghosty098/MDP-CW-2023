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
import android.widget.Toast;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class QRPage extends AppCompatActivity {
    Button btnCamera;
    ImageButton btnBack, btnQRGenerator;

    String qrCode1 = "QRBumper";
    String qrCode2 = "QRTurbo";
    String qrCode3 = "QRDoor";




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
        if (result.getContents() != null) {
            String scannedData = result.getContents();

            // Check the scanned content against identifiers
            if (scannedData.equals(qrCode1)) {
                // Open Activity A
                Intent intentA = new Intent(QRPage.this, DetailSparePart.class);
                startActivity(intentA);
            } else if (scannedData.equals(qrCode2)) {
                // Open Activity B
                Intent intentB = new Intent(QRPage.this, DetailSparePart2.class);
                startActivity(intentB);
            }
            else if (scannedData.equals(qrCode3)) {
                // Open Activity B
                Intent intentC = new Intent(QRPage.this, DetailSparePart3.class);
                startActivity(intentC);
            }
            else {
                // Handle other cases or show an error message
                Toast.makeText(QRPage.this, "Invalid QR code data", Toast.LENGTH_SHORT).show();
            }
        }
    });
}