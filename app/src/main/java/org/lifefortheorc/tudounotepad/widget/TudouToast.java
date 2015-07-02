package org.lifefortheorc.tudounotepad.widget;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by geminiwen on 15/7/2.
 */
public class TudouToast {
    private static Context sAppContext;
    private static Toast sToast;

    public static void init(Context c) {
        sAppContext = c;
    }


    public static void show(String text, int duration) {
        if (sToast != null) {
            sToast.cancel();
        }
        sToast = Toast.makeText(sAppContext, text, duration);
        sToast.show();
    }

    public static void show(String text) {
        TudouToast.show(text, Toast.LENGTH_LONG);
    }

    public static void show(int text, int duration) {
        if (sToast != null) {
            sToast.cancel();
        }
        sToast = Toast.makeText(sAppContext, text, duration);
        sToast.show();
    }

    public static void show(int text) {
        TudouToast.show(text, Toast.LENGTH_LONG);
    }
}
