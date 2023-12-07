package com.example.cw;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailSparePart extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String sparePartId = "sparePart1";
    Button btnSold;
    Long quantity;

    ImageButton btnBack;
    TextView textView3, locationTextView, qtyNumberTextView, priceNumberTextView, colourNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_spare_part);

        // Initialize UI components
        btnBack = findViewById(R.id.back);
        btnSold = findViewById(R.id.soldBtn);
        textView3 = findViewById(R.id.textView3);
        locationTextView = findViewById(R.id.location);
        qtyNumberTextView = findViewById(R.id.qtyNumber);
        priceNumberTextView = findViewById(R.id.priceNumber);
        colourNumber = findViewById(R.id.colourNumber);

        // Set up the back button click listener
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                startActivity(intent);
                finish();
            }
        });

        Log.d("Firestore", "Before Firestore retrieval");

        // Retrieve data from Firestore
        DocumentReference sparePartRef = db.collection("SpareParts").document(sparePartId);

        sparePartRef.get().addOnSuccessListener(documentSnapshot -> {
            Log.d("Firestore", "Inside Firestore onSuccess");
            if (documentSnapshot.exists()) {
                // Retrieve data
                String name = documentSnapshot.getString("Name");
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
                textView3.setText(name);
                locationTextView.setText("Location: " + location);
                qtyNumberTextView.setText(String.valueOf(quantity));
                priceNumberTextView.setText("$" + String.valueOf(price));
                colourNumber.setText(String.valueOf(colour));

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

        // Set click listener for the "Sold" button
        btnSold.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Decrease the quantity in Firestore
                decreaseQuantity(sparePartRef, quantity);

                // Update the displayed quantity in the UI
                qtyNumberTextView.setText(String.valueOf(quantity - 1));
            }
        });
    }

    private void decreaseQuantity(DocumentReference sparePartRef, long currentQuantity) {
        // Decrease the quantity by 1 in Firestore
        if (currentQuantity > 0) {
            sparePartRef.update("Quantity", currentQuantity - 1)
                    .addOnSuccessListener(aVoid -> {
                        Log.d("Firestore", "Quantity decreased successfully");
                        // Handle success if needed
                    })
                    .addOnFailureListener(e -> {
                        Log.e("Firestore", "Error decreasing quantity", e);
                        // Handle failure if needed
                    });
        }
    }
}
