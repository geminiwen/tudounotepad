package org.lifefortheorc.tudounotepad.base

import android.view.MenuItem
import me.imid.swipebacklayout.lib.app.SwipeBackActivity

/**
 * Created by Anchorer/duruixue on 2015/7/21.
 */
open class BaseSwipeActivity(): SwipeBackActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        when (id) {
            android.R.id.home -> {
                onBack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        onBack()
    }

    fun onBack() {
        var layout = getSwipeBackLayout()
        layout.scrollToFinishActivity()
    }
}