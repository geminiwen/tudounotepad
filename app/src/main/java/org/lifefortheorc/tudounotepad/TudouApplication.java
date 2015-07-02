package org.lifefortheorc.tudounotepad;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

import org.lifefortheorc.tudounotepad.widget.TudouToast;

/**
 * Created by geminiwen on 15/7/1.
 */
public class TudouApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        TudouToast.init(this);
    }
}
