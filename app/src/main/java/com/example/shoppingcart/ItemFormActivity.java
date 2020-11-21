package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ItemFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_form);

        setTitle("Add new item");

        Button addNewButton = findViewById(R.id.addNewButtonItemForm);
        EditText etName = findViewById(R.id.itemNameItemForm);
        EditText etQuantity = findViewById(R.id.itemQuantityItemForm);

        //TODO intentként kapja meg a cart nevét/id-ját

        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItemName = etName.getText().toString();
                String newItemQuantity = etQuantity.getText().toString();

                if(validation(newItemName, newItemQuantity, etName, etQuantity)) {
                    Toast.makeText(ItemFormActivity.this, "Add new item", Toast.LENGTH_SHORT).show();
                }
                // TODO új elem hozzáadása a backendhez
            }
        });

    }

    //Returns false if one of the data is empty
    Boolean validation(String newItemName, String newItemQuantity, EditText etName, EditText etQuantity) {
        Boolean validationIsOk = true;

        if(newItemName.isEmpty()) {
            validationIsOk = false;
            etName.setError("This field is required!");
        }

        if(newItemQuantity.isEmpty()) {
            validationIsOk = false;
            etQuantity.setError("This field is required!");
        }

        return validationIsOk;
    }
}