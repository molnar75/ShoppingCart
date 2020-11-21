package com.example.shoppingcart;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ShoppingCartListAdapter extends ArrayAdapter<ShoppingCart> {
    private static final String TAG = "ShoppingCartListAdapter";

    private Context mContext;
    int mResource;
    ShowPopup showPopup = new ShowPopup();

    public ShoppingCartListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ShoppingCart> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
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

        cartMenu.setTag(position);

        tvName.setText(name);
        checkBox.setText("(" + percentage + "%)");
        checkBox.setChecked(isItDone); //TODO chekbox változás implementálás

        cartMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingCart cart = getItem(position);
                showPopup.showPopup(v, mContext, cart);
            }
        });

        return convertView;
    }

}
