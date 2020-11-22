package com.example.shoppingcart;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ShowMenu implements PopupMenu.OnMenuItemClickListener{

    Context context;
    Object object;

    public void showMenu(View v, Context myContext, Object myObject) {

        this.context = myContext;
        this.object = myObject;

        PopupMenu popup = new PopupMenu(context, v);
        popup.inflate(R.menu.about_menu);
        popup.setOnMenuItemClickListener(this);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        ShoppingCart cart = null;
        CartItem cartItem = null;

        if(this.object instanceof ShoppingCart) {
            cart = (ShoppingCart) this.object;
        } else {
            cartItem = (CartItem) this.object;
        }

        switch (item.getItemId()) {
            case R.id.menuDelete:
                if(cart != null) {
                    new AlertDialog.Builder(context)
                            .setTitle("Confirmation popup")
                            .setMessage("Do you really want to delete "+ cart.getName() + "?")
                            .setPositiveButton("yes", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    ShoppingCart cart = (ShoppingCart) ShowMenu.this.object;
                                    Toast.makeText(context, "Delete " + cart.getName(), Toast.LENGTH_SHORT).show();
                                    //TODO törölni a kiválasztott cartot
                                }})
                            .setNegativeButton("no", null).show();
                } else {
                    new AlertDialog.Builder(context)
                            .setTitle("Confirmation popup")
                            .setMessage("Do you really want to delete "+ cartItem.getName() + "?")
                            .setPositiveButton("yes", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    CartItem cartItem = (CartItem) ShowMenu.this.object;
                                    Toast.makeText(context, "Delete " + cartItem.getName(), Toast.LENGTH_SHORT).show();
                                    //TODO törölni a kiválasztott itemet
                                }})
                            .setNegativeButton("no", null).show();
                }
                return true;
            case R.id.menuInformation:
                if(cart != null) {
                    //get the layout
                    LayoutInflater inflater = LayoutInflater.from(context);
                    View content = inflater.inflate(R.layout.cart_information_dialog_layout, null);
                    //set the cart information in the layout
                    TextView cartName = content.findViewById(R.id.cartNameInformationPopup);
                    TextView cartId = content.findViewById(R.id.cartIdInformationPopup);
                    TextView cartCreationDate = content.findViewById(R.id.cartCreateDateInformationPopup); //TODO add creation and modification date to cart
                    TextView cartModificationDate = content.findViewById(R.id.cartModificationDateInformationPopup);
                    cartName.setText(cart.getName());
                    cartId.setText(String.valueOf(cart.getId()));
                    new AlertDialog.Builder(context)
                            .setTitle("Information about " + cart.getName())
                            .setView(content)
                            .setNegativeButton("Cancel", null).show();
                } else {
                    //get the layout
                    LayoutInflater inflater = LayoutInflater.from(context);
                    View content = inflater.inflate(R.layout.item_information_dialog_layout, null);
                    //set the cart information in the layout
                    TextView itemName = content.findViewById(R.id.itemNameInformationPopup);
                    TextView itemQuantity = content.findViewById(R.id.itemQuantityInformationPopup);
                    TextView itemDescription = content.findViewById(R.id.itemDescriptionInformationPopup);
                    itemName.setText(cartItem.getName());
                    itemQuantity.setText(String.valueOf(cartItem.getQuantity()));
                    itemDescription.setText(cartItem.getDescription());
                    new AlertDialog.Builder(context)
                            .setTitle("Information about " + cartItem.getName())
                            .setView(content)
                            .setNegativeButton("Cancel", null).show();
                }
                return true; //TODO szerkesztés?
            default:
                return false;
        }
    }
}
