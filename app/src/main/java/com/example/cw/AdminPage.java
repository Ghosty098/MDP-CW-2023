package com.example.cw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPage extends AppCompatActivity {

    Button btnAdd;
    Button btnRemove;
    Button btnEdit;
    Button btnStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        btnAdd = findViewById(R.id.addSpareBtn);
        btnRemove = findViewById(R.id.removeSpareBtn);
        btnEdit = findViewById(R.id.editSpareBtn);
        btnStats = findViewById(R.id.statBtn);

        //Onclick listener for the Add button in the Admin Page
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddSparePart.class);
                startActivity(intent);
                finish();
            }
        });

        //Onclick listener for the Remove button in the Admin Page
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddSparePart.class); //change this after creation
                startActivity(intent);
                finish();
            }
        });

        //Onclick listener for the Edit button in the Admin Page
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditSparePart.class);
                startActivity(intent);
                finish();
            }
        });

        //Onclick listener for the Statistics button in the Admin Page
        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StatisticsPage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}