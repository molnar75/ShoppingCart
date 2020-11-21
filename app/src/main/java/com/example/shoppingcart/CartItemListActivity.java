package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class CartItemListActivity extends CartListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_items);

        setTitle("<cartName> Items");

        ListView cartItemList = findViewById(R.id.CartList);
        FloatingActionButton addNewItem = findViewById(R.id.addNewItemButton);

        Intent incomingIntent = getIntent();
        ArrayList<CartItem> itemList = (ArrayList<CartItem>) incomingIntent.getSerializableExtra("CartItem");

        if(itemList.isEmpty()) {
            TextView noItemMessage = findViewById(R.id.noItemMessage);
            noItemMessage.setText("This cart is empty! Add some items!");
        }

        CartItemListAdapter adapter = new CartItemListAdapter(this, R.layout.item_list_adapter_layout, itemList);
        cartItemList.setAdapter(adapter);

        //On add new button click opens the form where you can add new item
        addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartItemListActivity.this, ItemFormActivity.class);
                startActivity(intent);
            }
        });
    }
}