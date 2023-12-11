package com.example.cw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.android.material.button.MaterialButton$InspectionCompanion;
import com.google.firebase.firestore.DocumentReference;

public class EditSparePart extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageButton btnBack;
    Spinner spinnerColour;
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_spare_part);
        spinnerColour = findViewById(R.id.colourPartSpinner);
        btnBack = findViewById(R.id.back);

        ArrayAdapter<CharSequence> adapterColour = ArrayAdapter.createFromResource(
                this,
                R.array.car_colour_options,
                android.R.layout.simple_spinner_item
        );
        adapterColour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColour.setAdapter(adapterColour);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                startActivity(intent);
                finish();
            }
        });

        Log.d("Firestore", "Before Firestore retrieval");
        //DocumentReference sparePartRef = db.collection("SpareParts").document(sparePartId);

    }
}