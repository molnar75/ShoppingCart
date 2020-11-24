package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started");

        if(SaveSharedPreference.getUserName(MainActivity.this).length() == 0)
        {
            //If user is not logged in
            setTitle("Login");

            EditText loginName = findViewById(R.id.loginName);
            EditText loginPassword = findViewById(R.id.loginPassword);
            Button loginButton = findViewById(R.id.loginButton);
            Button registerButton = findViewById(R.id.registerButton);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = loginName.getText().toString();
                    String password = loginPassword.getText().toString();

                    if(validation(name, password, loginName, loginPassword)) {
                        Intent intent = new Intent(MainActivity.this, CartListActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });

            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, RegisterFormActivity.class);
                    startActivity(intent);
                }
            });
        }
        else
        {
            //If user is currently logged in
            Intent intent = new Intent(MainActivity.this, CartListActivity.class);
            startActivity(intent);
            finish();
        }

    }

    //Returns false if one of the data is empty
    Boolean validation(String name, String password, EditText loginName, EditText loginPassword) {
        Boolean validationIsOk = true;

        if(name.isEmpty()) {
            validationIsOk = false;
            loginName.setError("This field is required!");
        }

        if(password.isEmpty()) {
            validationIsOk = false;
            loginPassword.setError("This field is required!");
        }

        String correctName = "user";
        String correctPassword = "user"; //TODO backendről kérni majd az adatokat
        TextView errorMessageView = findViewById(R.id.loginErrorMessage);
        String errorMessage = "Name or password is incorrect!";

        if((name.equals(correctName)) && password.equals(correctPassword)) {
            SaveSharedPreference.setUserName( MainActivity.this, correctName);
        } else {
            validationIsOk = false;
            errorMessageView.setText(errorMessage);
        }

        return validationIsOk;
    }

    //TODO confirmation popup style változtatása
    //TODO bónusz nyelviesítés
}