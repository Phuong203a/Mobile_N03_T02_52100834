package com.example.bai2;

import android.content.Context;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MyToast {
    static Toast toast;

    public static void toast(Context context, CharSequence msg) {
        if (toast!= null) {
            toast.cancel();
        }
        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.show();
    }


}
