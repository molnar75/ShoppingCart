package com.example.shoppingcart;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ShoppingCartListAdapter extends ArrayAdapter<ShoppingCart> {
    private static final String TAG = "ShoppingCartListAdapter";

    private Context mContext;
    int mResource;
    ArrayList<ShoppingCart> cartList;
    ArrayList<ShoppingCart> cartsToChange = new ArrayList<>();
    ShowMenu showMenu = new ShowMenu();

    public ShoppingCartListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ShoppingCart> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.cartList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Log.d(TAG, "getView: Started.");

        //get the cart information
        String name = getItem(position).getName();
        String percentage = String.valueOf(getItem(position).getPercentage());
        Boolean isItDone = getItem(position).isItDone();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = convertView.findViewById(R.id.cartName);
        CheckBox checkBox = convertView.findViewById(R.id.cartCheckbox);
        ImageButton cartMenu = convertView.findViewById(R.id.cartMenu);

        ViewGroup buttonView = (ViewGroup) parent.getParent().getParent(); //get the linearLayout where the button is
        FloatingActionButton saveButton = buttonView.findViewById(R.id.saveCartStatusButton);

        cartMenu.setTag(position);

        tvName.setText(name);
        checkBox.setText("(" + percentage + "%)");
        checkBox.setChecked(isItDone); //TODO chekbox változás implementálás

        cartMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingCart cart = getItem(position);
                showMenu.showMenu(v, mContext, cart);
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveButton.setVisibility(View.VISIBLE);
                String cartName = tvName.getText().toString();
                addCartToList(cartName, isChecked);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("Confirmation popup")
                        .setMessage("Do you really want to save the statuses?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(mContext, "Yes", Toast.LENGTH_SHORT).show();
                                //TODO változások mentése és cartsToChange lista nullázása.
                            }})
                        .setNegativeButton("no", null).show();
            }
        });

        return convertView;
    }

    private void addCartToList(String cartName, boolean isChecked) {
        ShoppingCart cartToChange = new ShoppingCart(0, "", 0, false);
        for (ShoppingCart cart: cartList) {
            if(cart.getName().equals(cartName)) {
                cartToChange = cart;
            }
        }
        cartToChange.setItDone(isChecked);
        cartsToChange.add(cartToChange);
    }

}
