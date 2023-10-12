package com.example.bai3;

import android.content.Context;
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
