package com.example.cw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RemoveSparePart extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ImageButton btnBack;

    Button btnDelete1, btnDelete2, btnDelete3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_spare_part);
        btnBack = findViewById(R.id.back);
        btnDelete1 = findViewById(R.id.delete);
        btnDelete2 = findViewById(R.id.delete2);
        btnDelete3 = findViewById(R.id.delete3);


        //Button OnClick listener for the Back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                startActivity(intent);
                finish();
            }
        });

        //Button OnClick listener for the Delete-1 button
        btnDelete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call a method to delete spare part with ID 1 from the database
                deleteSparePartFromDatabase("sparePart_1702308024328");
            }
        });

        //Button OnClick listener for the Delete-2 button
        btnDelete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call a method to delete spare part with ID 2 from the database
                deleteSparePartFromDatabase("2");
            }
        });

        //Button OnClick listener for the Delete-3 button
        btnDelete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call a method to delete spare part with ID 3 from the database
                deleteSparePartFromDatabase("3");
            }
        });
    }

    //This method deletes the spare part from the database
    private void deleteSparePartFromDatabase(String sparePartId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference sparePartRef = db.collection("SpareParts").document(sparePartId);

        // Delete the spare part from the database
        sparePartRef.delete()
                .addOnSuccessListener(aVoid -> {
                    showToast("Spare part added successfully");

                })
                .addOnFailureListener(e -> {
                    showToast("Failed to add spare part");
                });
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}