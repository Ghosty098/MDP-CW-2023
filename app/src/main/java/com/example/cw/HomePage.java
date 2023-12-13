package com.example.cw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity {

    FirebaseAuth auth;
    ImageButton btnLogOut;
    ImageButton btnAdmin;
    ImageButton btnQr;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        auth = FirebaseAuth.getInstance();
        btnAdmin = findViewById(R.id.admin);
        btnQr = findViewById(R.id.qrCode);
        btnLogOut = findViewById(R.id.logOut);
        user = auth.getCurrentUser();

        //This checks if any users are currently logged in, if not it sends the user to the Log-in Page
        if(user == null){
            Intent intent = new Intent(getApplicationContext(), LogIn.class);
            startActivity(intent);
            finish();
        }

        //Button OnClick listener for the Log-out button
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LogIn.class);
                startActivity(intent);
                finish();
            }
        });

        //Button OnClick listener for the Admin button
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                startActivity(intent);
                finish();
            }
        });

        //Button OnClick listener for the QR Code button
        btnQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QRPage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //These methods are to redirect the user to the specific pages after clicking on the CardView
    public void toDetailSparePart(View view){
        Intent intent = new Intent(getApplicationContext(), DetailSparePart.class);
        startActivity(intent);
        finish();
    }

    public void toDetailSparePart2(View view){
        Intent intent = new Intent(getApplicationContext(), DetailSparePart2.class);
        startActivity(intent);
        finish();
    }

    public void toDetailSparePart3(View view){
        Intent intent = new Intent(getApplicationContext(), DetailSparePart3.class);
        startActivity(intent);
        finish();
    }

}