package org.lifefortheorc.tudounotepad.activity

import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import me.imid.swipebacklayout.lib.app.SwipeBackActivity
import org.lifefortheorc.tudounotepad.R
import org.lifefortheorc.tudounotepad.base.BaseSwipeActivity
import org.lifefortheorc.tudounotepad.model.NoteModel
import org.lifefortheorc.tudounotepad.widget.TudouToast

/**
 * 编辑页面
 * Created by Anchorer/duruixue on 2015/7/13.
 */
public class EditActivity(): BaseSwipeActivity() {
    var mEditText: EditText? = null
    var mNote = NoteModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
    }

    override fun onContentChanged() {
        super.onContentChanged()

        var actionBar: ActionBar? = getActionBar()
        actionBar?.setDisplayHomeAsUpEnabled(true)

        mEditText = findViewById(R.id.et_content) as EditText
        mEditText?.setTransitionName("content")

        var intent = getIntent()
        var id = intent.getLongExtra("id", -1L)
        if (id != -1L) {
            mNote = NoteModel.queryById(id)
            var content = mNote.content
            mEditText?.setText(content)
            mEditText?.requestFocus()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.getItemId()
        when (id) {
            R.id.save -> {
                var content = mEditText?.getText().toString()
                if (TextUtils.isEmpty(content)) {
                    TudouToast.show(R.string.no_content_no_save)
                    return true
                }

                mNote.time = System.currentTimeMillis()
                mNote.content = content
                mNote.save()

                if (TextUtils.isEmpty(content)) {
                    mNote.delete()
                }

                TudouToast.show(R.string.toast_save_success)
                onBack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        var menuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.menu_edit, menu)
        return super.onCreateOptionsMenu(menu)
    }

}