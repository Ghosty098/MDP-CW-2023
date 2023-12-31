package com.example.cw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddSparePart extends AppCompatActivity {

    ImageButton btnBack;
    Spinner spinnerColourPart, spinnerQuantity;
    EditText carPart;
    EditText editTextPrice, editTextCarPart, editTextLocation;

    Spinner colourPartSpinner, quantitySpinner;

    Button btnQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spare_part);

        btnBack = findViewById(R.id.back);
        spinnerColourPart = findViewById(R.id.colourPartSpinner);
        spinnerQuantity = findViewById(R.id.quantitySpinner);
        carPart = findViewById(R.id.carPart);
        btnQR = findViewById(R.id.createQRBtn);

        //Adapter for the spinner of the colours
        ArrayAdapter<CharSequence> adapterColour = ArrayAdapter.createFromResource(
                this,
                R.array.car_colour_options,
                android.R.layout.simple_spinner_item
        );
        adapterColour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColourPart.setAdapter(adapterColour);

        //Adapter for the spinner of the quantity
        ArrayAdapter<CharSequence> adapterQuantity = ArrayAdapter.createFromResource(
                this,
                R.array.quantity_options,
                android.R.layout.simple_spinner_item
        );
        adapterQuantity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuantity.setAdapter(adapterQuantity);

        //Button OnClick listener for the Back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                startActivity(intent);
                finish();
            }
        });

        btnQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QRGenerator.class);
                startActivity(intent);
                finish();
            }
        });


        editTextPrice = findViewById(R.id.price);
        editTextCarPart = findViewById(R.id.carPart);
        editTextLocation = findViewById(R.id.carLocation);
        colourPartSpinner = findViewById(R.id.colourPartSpinner);
        quantitySpinner = findViewById(R.id.quantitySpinner);


        Button addButton = findViewById(R.id.addSpareBtn);

        //Button OnClick listener for the Add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get data from UI components
                String price = editTextPrice.getText().toString();
                String carPart = editTextCarPart.getText().toString();
                String colour = colourPartSpinner.getSelectedItem().toString();
                String quantity = quantitySpinner.getSelectedItem().toString();
                String locationAdd = editTextLocation.getText().toString();

                // Create a new spare part map
                Map<String, Object> sparePart = new HashMap<>();
                sparePart.put("Price", Double.parseDouble(price));
                sparePart.put("Car Part", carPart);
                sparePart.put("Colour", colour);
                sparePart.put("Location", locationAdd);
                sparePart.put("Quantity", Integer.parseInt(quantity));

                // Add the spare part to Firestore
                addSparePart(sparePart);
            }
        });
    }

    //This method adds the spare part to the database
    private void addSparePart(Map<String, Object> sparePart) {
        // Access a Cloud Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Generate a unique ID by appending a timestamp to your custom ID
        String customDocumentId = "sparePart_" + System.currentTimeMillis();

        // Add a new document with the custom ID
        db.collection("SpareParts")
                .document(customDocumentId)
                .set(sparePart)
                .addOnSuccessListener(aVoid -> { // Handle success
                    showToast("Spare part added successfully");
                    Log.d("MSG","Part added successful");
                })
                .addOnFailureListener(e -> { // Handle failure
                    showToast("Failed to add spare part");
                });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
