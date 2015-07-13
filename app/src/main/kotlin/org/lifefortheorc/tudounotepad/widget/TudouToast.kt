package org.lifefortheorc.tudounotepad.widget

import android.content.Context
import android.widget.Toast
import org.lifefortheorc.tudounotepad.widget.TudouToast

/**
 * Created by geminiwen on 15/7/13.
 */

object TudouToast {
    var context: Context? = null
    var toast: Toast? = null

    public fun init(ctx : Context) {
        this.context = ctx;
    }

    public fun show(text: String, duration: Int) {
        toast?.cancel()
        toast = Toast.makeText(context, text, duration)
        toast?.show()
    }

    public fun show(text: String) {
        show(text, Toast.LENGTH_LONG)
    }

    public fun show(text: Int, duration: Int) {
        toast?.cancel()
        toast = Toast.makeText(context, text, duration)
        toast?.show()
    }

    public fun show(text: Int) {
        show(text, Toast.LENGTH_LONG)
    }
}
