package org.lifefortheorc.tudounotepad.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import me.imid.swipebacklayout.lib.app.SwipeBackActivity
import org.lifefortheorc.tudounotepad.R
import org.lifefortheorc.tudounotepad.model.NoteModel

/**
 * Created by geminiwen on 15/7/10.
 */
public class ViewActivity(): SwipeBackActivity() {
    var textViewContent: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
    }

    override fun onContentChanged() {
        super.onContentChanged()

        val actionBar = getActionBar()
        actionBar.setDisplayHomeAsUpEnabled(true)

        textViewContent = findViewById(R.id.tv_content) as TextView

        textViewContent?.setTransitionName("content")

        val intent = getIntent()
        val id = intent.getLongExtra("id", -1L)

        if (id == -1L) {
            this.finish()
            return
        }

        val note = NoteModel.queryById(id)
        val content = note.content
        textViewContent?.setText(content)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        when (id) {
            android.R.id.home -> {
                this.finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
