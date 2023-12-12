package com.example.cw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.android.material.button.MaterialButton$InspectionCompanion;
import com.google.firebase.firestore.DocumentReference;

public class EditSparePart extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String sparePartId = "sparePart_1702288971024";
    ImageButton btnBack;
    Button btnEdit;
    EditText editQty, editPrice, editColour;
    Long quantity;
    TextView carPart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_spare_part);
        btnBack = findViewById(R.id.back);
        editQty = findViewById(R.id.qtyEditNumber);
        editPrice = findViewById(R.id.priceEditNumber);
        carPart = findViewById(R.id.partName);
        editColour = findViewById(R.id.colourPartEdit);
        btnEdit = findViewById(R.id.editSpareBtn);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                startActivity(intent);
                finish();
            }
        });

        Log.d("Firestore", "Before Firestore retrieval");
        DocumentReference sparePartRef = db.collection("SpareParts").document(sparePartId);

        sparePartRef.get().addOnSuccessListener(documentSnapshot -> {
            Log.d("Firestore", "Inside Firestore onSuccess");
            if (documentSnapshot.exists()) {
                // Retrieve data
                String name = documentSnapshot.getString("Car Part");
                String location = documentSnapshot.getString("Location");
                quantity = documentSnapshot.getLong("Quantity");
                double price = documentSnapshot.getDouble("Price");
                String colour = documentSnapshot.getString("Colour");

                // Log the retrieved values
                Log.d("Firestore", "Name: " + name);
                Log.d("Firestore", "Location: " + location);
                Log.d("Firestore", "Quantity: " + quantity);
                Log.d("Firestore", "Price: " + price);

                // Now you can use these values to populate your UI components
                carPart.setText(name);
                editQty.setText(String.valueOf(quantity));
                editPrice.setText( String.valueOf(price));
                editColour.setText(String.valueOf(colour));

                // ... and so on
            } else {
                // Document does not exist
                Log.d("Firestore", "Document does not exist");
            }
        }).addOnFailureListener(e -> {
            // Handle errors
            Log.e("Firestore", "Firestore retrieval failure", e);
        });

        Log.d("Firestore", "After Firestore retrieval");

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve new values from UI components
                String newQty = editQty.getText().toString();
                String newPrice = editPrice.getText().toString();
                String newColour = editColour.getText().toString();

                // Update the Firestore document with new values
                sparePartRef.update(
                        "Quantity", Long.parseLong(newQty),
                        "Price", Double.parseDouble(newPrice),
                        "Colour", newColour
                ).addOnSuccessListener(aVoid -> {
                    Log.d("Firestore", "Document updated successfully");

                    // Optionally, you can perform any additional actions after a successful update
                }).addOnFailureListener(e -> {
                    Log.e("Firestore", "Error updating document", e);

                    // Handle errors during the update
                });
            }

        });

    }
}