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

        //Button OnClick listener for the Back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LogIn.class);
                startActivity(intent);
                finish();
            }
        });

        //Button OnClick listener for the QR Generator button
        btnQRGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QRGenerator.class);
                startActivity(intent);
                finish();
            }
        });

        //Button OnClick listener for the Camera button
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQrCode();
            }
        });


    }

    //This method is responsible for the Scanning of codes
    private void scanQrCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Scanning QR Code");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CameraCapture.class);
        barLauncher.launch(options); //launches the result of the QR code
    }


    //This barLauncher, opens the activities encoded in the QR code
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            String scannedData = result.getContents();

            // Check the scanned content against identifiers
            if (scannedData.equals(qrCode1)) {
                Intent intentA = new Intent(QRPage.this, DetailSparePart.class);
                startActivity(intentA);
            } else if (scannedData.equals(qrCode2)) {
                Intent intentB = new Intent(QRPage.this, DetailSparePart2.class);
                startActivity(intentB);
            }
            else if (scannedData.equals(qrCode3)) {
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