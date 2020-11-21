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

import java.util.ArrayList;

public class CartItemListAdapter extends ArrayAdapter<CartItem> {
    private static final String TAG = "CartItemListAdapter";

    private Context mContext;
    int mResource;
    ShowPopup showPopup = new ShowPopup();
    ArrayList<CartItem> itemList;
    ArrayList<CartItem> itemsToChange = new ArrayList();

    public CartItemListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CartItem> itemList) {
        super(context, resource, itemList);
        this.mContext = context;
        this.mResource = resource;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Log.d(TAG, "getView: Started ");

        //get the cart information
        String name = getItem(position).getName();
        String quantity = String.valueOf(getItem(position).getQuantity());
        Boolean isItDone = getItem(position).isItDone();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = convertView.findViewById(R.id.itemName);
        TextView tvQuantity = convertView.findViewById(R.id.itemQuantity);
        CheckBox checkBox = convertView.findViewById(R.id.cartCheckbox);
        ImageButton itemMenu = convertView.findViewById(R.id.itemMenu);

        ViewGroup buttonView = (ViewGroup) parent.getParent(); //get the linearLayout where the button is
        Button saveButton = buttonView.findViewById(R.id.saveItemStatus);

        tvName.setText(name);
        tvQuantity.setText(quantity);
        checkBox.setChecked(isItDone);

        itemMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = getItem(position);
                showPopup.showPopup(v, mContext, item);
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveButton.setVisibility(View.VISIBLE);
                String itemName = tvName.getText().toString();
                addItemToList(itemName, isChecked);
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
                                //TODO változások mentése és itemToChange lista nullázása.
                            }})
                        .setNegativeButton("no", null).show();
            }
        });

        return convertView;
    }

    private void addItemToList(String itemName, boolean isChecked) {
        CartItem itemToChange = new CartItem("", "", 0, false);
        for (CartItem item: itemList) {
            if(item.getName().equals(itemName)) {
                itemToChange = item;
            }
        }
        itemToChange.setItDone(isChecked);
        itemsToChange.add(itemToChange);
    }
}
