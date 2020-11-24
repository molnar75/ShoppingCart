package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        setTitle("Register");

        EditText etUsername = findViewById(R.id.userNameRegisterForm);
        EditText etPassword = findViewById(R.id.passwordRegisterForm);
        EditText etPasswordAgain = findViewById(R.id.passwordAgainRegisterForm);
        TextView tvErrorMessage = findViewById(R.id.registerFormErrorMessage);
        Button addNewButton = findViewById(R.id.addNewButtonRegisterForm);

        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String passwordAgain = etPasswordAgain.getText().toString();
                if(validation(username, password, passwordAgain, etUsername, etPassword, etPasswordAgain, tvErrorMessage)) {
                    Toast.makeText(RegisterFormActivity.this, "Add new user", Toast.LENGTH_SHORT).show();
                    //TODO add new user and open main activity
                }
            }
        });
    }

    //Returns false if one of the data is empty
    Boolean validation(String username, String password, String passwordAgain, EditText etUsername, EditText etPassword, EditText etPasswordAgain, TextView tvErrorMessage) {
        Boolean validationIsOk = true;
        tvErrorMessage.setText("");

        if(username.isEmpty()) {
            validationIsOk = false;
            etUsername.setError("This field is required!");
        }

        if(password.isEmpty()) {
            validationIsOk = false;
            etPassword.setError("This field is required!");
        }

        if(passwordAgain.isEmpty()) {
            validationIsOk = false;
            etPasswordAgain.setError("This field is required!");
        }

        if(!password.equals(passwordAgain)) {
            validationIsOk = false;
            tvErrorMessage.setText("The passwords does not match!");
        }

        return validationIsOk;
    }
}