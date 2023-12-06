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

    public void toDetailSparePart(View view){
        Intent intent = new Intent(getApplicationContext(), DetailSparePart.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        auth = FirebaseAuth.getInstance();
        btnAdmin = findViewById(R.id.admin);
        btnQr = findViewById(R.id.qrCode);
        btnLogOut = findViewById(R.id.logOut);
        user = auth.getCurrentUser();
        if(user == null){
            Intent intent = new Intent(getApplicationContext(), LogIn.class);
            startActivity(intent);
            finish();
        }

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LogIn.class);
                startActivity(intent);
                finish();
            }
        });

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                startActivity(intent);
                finish();
            }
        });

        btnQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QRPage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}