package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CartFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_form);

        setTitle("Add new cart");

        Button addNewButton = findViewById(R.id.addNewButtonCartForm);
        EditText etName = findViewById(R.id.cartNameCartForm);
        EditText etId = findViewById(R.id.cartIDCartForm);

        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = etName.getText().toString();
                String newId = etId.getText().toString();

                if(validation(newName, newId, etName, etId)){
                    if(!newName.isEmpty()) {
                        //TODO backendhez új item névvel
                    } else {
                        //TODO felhasználóhoz hozáadni a cartot az id alapján
                    }
                }
            }
        });
    }

    //Returns false if both of the data is empty
    Boolean validation(String newCartName, String newCartId, EditText etName, EditText etId) {

        if(newCartName.isEmpty() && newCartId.isEmpty()) {
            etName.setError("One field is required");
            etId.setError("One field is required");

            return false;
        }

        return true;
    }
}