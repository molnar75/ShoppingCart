package com.example.shoppingcart;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

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
                    Toast.makeText(this.context, "Delete " + cart.getName(), Toast.LENGTH_SHORT).show(); //TODO törölni a kiválasztott cartot
                    //TODO confirmation ablak
                } else {
                    Toast.makeText(this.context, "Delete " + cartItem.getName(), Toast.LENGTH_SHORT).show(); //TODO törölni a kiválasztott itemet
                }
                return true;
            case R.id.menuInformation:
                if(cart != null) {
                    Toast.makeText(this.context, "Information about " + cart.getName(), Toast.LENGTH_SHORT).show(); //TODO infó panelt megnyitni a kiválasztott cartról
                } else {
                    Toast.makeText(this.context, "Information about " + cartItem.getName(), Toast.LENGTH_SHORT).show(); //TODO infó panelt megnyitni a kiválasztott elemről
                }
                return true; //TODO szerkesztés?
            default:
                return false;
        }
    }
}
