package com.example.cw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class AddSparePart extends AppCompatActivity {

    ImageButton btnBack;
    Spinner spinnerColourPart, spinnerQuantity;
    EditText carPart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spare_part);
        btnBack = findViewById(R.id.back);
        spinnerColourPart = findViewById(R.id.colourPartSpinner);
        spinnerQuantity = findViewById(R.id.quantitySpinner);
        carPart = findViewById(R.id.carPart);

        ArrayAdapter<CharSequence> adapterColour = ArrayAdapter.createFromResource(
                this,
                R.array.car_colour_options,
                android.R.layout.simple_spinner_item
        );
        adapterColour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColourPart.setAdapter(adapterColour);

        ArrayAdapter<CharSequence> adapterQuantity = ArrayAdapter.createFromResource(
                this,
                R.array.quantity_options,
                android.R.layout.simple_spinner_item
        );
        adapterQuantity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuantity.setAdapter(adapterQuantity);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                    startActivity(intent);
                    finish();
            }
        });
    }
}