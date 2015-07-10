package org.lifefortheorc.tudounotepad.activity

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.TextView
import butterknife.Bind
import butterknife.ButterKnife
import org.lifefortheorc.tudounotepad.R
import org.lifefortheorc.tudounotepad.model.NoteModel

/**
 * Created by geminiwen on 15/7/10.
 */
public class ViewActivity(): Activity() {
    var mTextViewContent: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_view)
    }

    override fun onContentChanged() {
        super.onContentChanged()

        val actionBar = getActionBar()
        actionBar.setDisplayHomeAsUpEnabled(true)

        mTextViewContent = findViewById(R.id.tv_content) as TextView

        mTextViewContent?.setTransitionName("content")

        val intent = getIntent()
        val id = intent.getLongExtra("id", -1L)

        if (id == -1L) {
            this.finish()
            return
        }

        val note = NoteModel.queryById(id)
        val content = note.getContent()
        mTextViewContent?.setText(content)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.getItemId()
        when (id) {
            android.R.id.home -> {
                this.finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
