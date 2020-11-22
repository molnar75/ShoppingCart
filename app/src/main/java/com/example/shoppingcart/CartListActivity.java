package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class CartListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        String userName = SaveSharedPreference.getUserName(CartListActivity.this);

        setTitle(userName + "'s Shopping Carts");

        ListView cartList = findViewById(R.id.CartList);
        FloatingActionButton addNewCart = findViewById(R.id.addNewCartButton);

        //create shopping carts
        ShoppingCart cart1 = new ShoppingCart("cart1", 12, false);
        ShoppingCart cart2 = new ShoppingCart("cart2", 20, false);
        ShoppingCart cart3 = new ShoppingCart("cart3", 43, false);
        ShoppingCart cart4 = new ShoppingCart("cart4", 0, false);
        ShoppingCart cart5 = new ShoppingCart("cart5", 50, true);
        ShoppingCart cart6 = new ShoppingCart("cart6", 70, true);
        ShoppingCart cart7 = new ShoppingCart("cart7", 100, true);
        ShoppingCart cart8 = new ShoppingCart("cart8", 65, false);
        ShoppingCart cart9 = new ShoppingCart("cart9", 32, false);
        ShoppingCart cart10 = new ShoppingCart("cart10", 14, false);
        ShoppingCart cart11 = new ShoppingCart("cart11", 40, true);
        ShoppingCart cart12 = new ShoppingCart("cart12", 7, false);

        ArrayList<ShoppingCart> shoppingCartList = new ArrayList<>();

        shoppingCartList.add(cart1);
        shoppingCartList.add(cart2);
        shoppingCartList.add(cart3);
        shoppingCartList.add(cart4);
        shoppingCartList.add(cart5);
        shoppingCartList.add(cart6);
        shoppingCartList.add(cart7);
        shoppingCartList.add(cart8);
        shoppingCartList.add(cart9);
        shoppingCartList.add(cart10);
        shoppingCartList.add(cart11);
        shoppingCartList.add(cart12);

        ShoppingCartListAdapter adapter = new ShoppingCartListAdapter(this, R.layout.cart_list_adapter_layout, shoppingCartList);
        cartList.setAdapter(adapter);

        ArrayList<CartItem> cartItemList = new ArrayList<>();

        //create some items to carts
        CartItem item1 = new CartItem("cart1", "item1","Some details", 1, false);
        CartItem item2 = new CartItem("cart1", "item2","",2, false);
        CartItem item3 = new CartItem("cart1", "item3","Something important",5, false);
        CartItem item4 = new CartItem("cart2", "item4","",1, true);
        CartItem item5 = new CartItem("cart2", "item5","",3, false);
        CartItem item6 = new CartItem("cart3", "item6","",1, false);
        CartItem item7 = new CartItem("cart3", "item7","",1, true);
        CartItem item8 = new CartItem("cart4", "item8","",4, false);

        cartItemList.add(item1);
        cartItemList.add(item2);
        cartItemList.add(item3);
        cartItemList.add(item4);
        cartItemList.add(item5);
        cartItemList.add(item6);
        cartItemList.add(item7);
        cartItemList.add(item8);

        //Opens the list of the items in the cart
        cartList.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(CartListActivity.this, CartItemListActivity.class);
            ArrayList<CartItem> intentList = getItems(cartItemList,shoppingCartList.get(i).getName());
            intent.putExtra("CartItem", (Serializable) intentList);
            startActivity(intent);
        });

        addNewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartListActivity.this, "Add new cart", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private ArrayList<CartItem> getItems(ArrayList<CartItem> cartItemList, String cartName) {
        ArrayList<CartItem> returnList = new ArrayList<>();
        for (int i = 0; i < cartItemList.size(); i++) {
            if(cartItemList.get(i).getCartName().equals(cartName)) {
                returnList.add(cartItemList.get(i));
            }
        }
        return returnList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);

        //add white color to logout icon
        for(int i = 0; i < menu.size(); i++){
            Drawable drawable = menu.getItem(i).getIcon();
            if(drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logOut) {
            SaveSharedPreference.clearUserName(CartListActivity.this);
            Intent intent = new Intent(CartListActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}