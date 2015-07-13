package org.lifefortheorc.tudounotepad

import android.app.Application
import com.activeandroid.ActiveAndroid
import org.lifefortheorc.tudounotepad.widget.TudouToast

/**
 * Created by geminiwen on 15/7/13.
 */

public class TudouApplication(): Application() {
    override fun onCreate() {
        super.onCreate()
        ActiveAndroid.initialize(this)
        TudouToast.init(this)
    }
}