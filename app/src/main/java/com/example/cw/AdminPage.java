package com.example.cw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AdminPage extends AppCompatActivity {

    Button btnAdd;
    Button btnRemove;
    Button btnEdit;
    Button btnStats;
    ImageButton btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        btnBack = findViewById(R.id.back);
        btnAdd = findViewById(R.id.addSpareBtn);
        btnRemove = findViewById(R.id.removeSpareBtn);
        btnEdit = findViewById(R.id.editSpareBtn);
        btnStats = findViewById(R.id.statBtn);

        //Button OnClick listener for the Add Spare Part button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddSparePart.class);
                startActivity(intent);
                finish();
            }
        });

        //Button OnClick listener for the Remove Spare Part button
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RemoveSparePart.class); //change this after creation
                startActivity(intent);
                finish();
            }
        });

        //Button OnClick listener for the Edit Spare Part button
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditSparePart.class);
                startActivity(intent);
                finish();
            }
        });

        //Button OnClick listener for the Statistics button
        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StatisticsPage.class);
                startActivity(intent);
                finish();
            }
        });

        //Button OnClick listener for the Back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                startActivity(intent);
                finish();
            }
        });

    }
}