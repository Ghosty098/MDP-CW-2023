package com.example.cw;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailSparePart2 extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance(); //Initialise a call to connect to Firestore
    String sparePartId = "sparePart_1702288971024"; //Specific spare part ID in the firebase
    Button btnSold, btnChangePrice;
    Long quantity;

    ImageButton btnBack;
    TextView textView3, locationTextView, qtyNumberTextView, priceNumberTextView, colourNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_spare_part2);

        btnBack = findViewById(R.id.back);
        btnSold = findViewById(R.id.soldBtn);
        btnChangePrice = findViewById(R.id.changePriceBtn);
        textView3 = findViewById(R.id.textView3);
        locationTextView = findViewById(R.id.location);
        qtyNumberTextView = findViewById(R.id.qtyNumber);
        priceNumberTextView = findViewById(R.id.priceNumber);
        colourNumber = findViewById(R.id.colourNumber);

        //Button OnClick listener for the Back button
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
                textView3.setText(name);
                locationTextView.setText("Location: " + location);
                qtyNumberTextView.setText(String.valueOf(quantity));
                priceNumberTextView.setText("$" + String.valueOf(price));
                colourNumber.setText(String.valueOf(colour));

            } else {
                Log.d("Firestore", "Document does not exist"); // Document does not exist
            }
        }).addOnFailureListener(e -> {

            Log.e("Firestore", "Firestore retrieval failure", e); // Handling errors
        });

        Log.d("Firestore", "After Firestore retrieval");

        //Button OnClick listener for the Sold button
        btnSold.setOnClickListener(new View.OnClickListener() {


            //This method, decrements the quantity for the spare part when sold button is clicked
            @Override
            public void onClick(View v) {
                // Decrease the quantity in Firestore
                decreaseQuantity(sparePartRef, quantity);

                // Update the displayed quantity in the UI
                qtyNumberTextView.setText(String.valueOf(quantity - 1));
            }
        });
        btnChangePrice.setOnClickListener(v -> showChangePriceDialog(sparePartRef));

    }

    //This method allows the user to change price of spare part
    private void showChangePriceDialog(DocumentReference sparePartRef) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change Price");

        // Create EditText to input the new price
        final EditText input = new EditText(this);
        input.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setView(input);

        // Prompting the user to enter the new price
        builder.setPositiveButton("OK", (dialog, which) -> {
            // Get the new price from the input field
            String newPriceStr = input.getText().toString();
            if (!newPriceStr.isEmpty()) {
                double newPrice = Double.parseDouble(newPriceStr);

                // Update the price in Firestore
                updatePrice(sparePartRef, newPrice);

                // Update the displayed price in the UI
                priceNumberTextView.setText("$" + newPrice);
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }



    //This method sends the new quantity to the database
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

    //This method sends the updated price to the database
    private void updatePrice(DocumentReference sparePartRef, double newPrice) {
        sparePartRef.update("Price", newPrice)
                .addOnSuccessListener(aVoid -> Log.d("Firestore", "Price updated successfully"))
                .addOnFailureListener(e -> Log.e("Firestore", "Error updating price", e));
    }



}
