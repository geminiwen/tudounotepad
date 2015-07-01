package org.lifefortheorc.tudounotepad.activity;

import android.app.Activity;
import android.os.Bundle;

import org.lifefortheorc.tudounotepad.R;

/**
 * 这是应用主界面。
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }
}
