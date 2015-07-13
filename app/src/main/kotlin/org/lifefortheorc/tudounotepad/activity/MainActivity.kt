package org.lifefortheorc.tudounotepad.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Outline
import android.media.MediaRouter
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.TextView
import org.lifefortheorc.tudounotepad.R
import org.lifefortheorc.tudounotepad.adapter.NoteAdapter
import org.lifefortheorc.tudounotepad.model.NoteModel
import org.lifefortheorc.tudounotepad.widget.EmptyRecyclerView

/**
 * Created by Anchorer/duruixue on 2015/7/13.
 */
public class MainActivity(): Activity(), View.OnClickListener {
    var mAddBtn: FloatingActionButton? = null
    var mRecyclerNote: EmptyRecyclerView? = null
    var mTextViewEmpty: TextView? = null
    var mNoteAdapter: NoteAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super<Activity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var intent = getIntent()
        handleSend(intent)
    }

    fun handleSend(intent: Intent) {
        var action = intent.getAction()
        if (action.equals(Intent.ACTION_SEND)) {
            var content = intent.getStringExtra(Intent.EXTRA_TEXT)
            var time = System.currentTimeMillis()
            var note = NoteModel()
            note.setContent(content)
            note.setTime(time)
            note.save()
        }
    }

    override fun onContentChanged() {
        super<Activity>.onContentChanged()

        mAddBtn = findViewById(R.id.fab_add) as FloatingActionButton
        mRecyclerNote = findViewById(R.id.recycler_note) as EmptyRecyclerView
        mTextViewEmpty = findViewById(R.id.tv_empty) as TextView

        mAddBtn?.setOnClickListener(this)

        mNoteAdapter = NoteAdapter(this)
        mRecyclerNote?.setLayoutManager(LinearLayoutManager(this))
        mRecyclerNote?.setAdapter(mNoteAdapter)
        mRecyclerNote?.setEmptyView(mTextViewEmpty)

        /**
         * 为添加按钮描边
         */
        mAddBtn?.setOutlineProvider(object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                var width = view?.getWidth() as Int
                var height = view?.getHeight() as Int
                outline?.setOval(0, 0, width, height)
            }
        })
    }

    override fun onResume() {
        super<Activity>.onResume()
        var noteList = NoteModel.queryNoteList()
        mNoteAdapter?.setNoteList(noteList)
        mNoteAdapter?.notifyDataSetChanged()
    }

    override fun onClick(view: View?) {
        when (view) {
            mAddBtn -> {
                var intent = Intent(this, javaClass<EditActivity>())
                startActivity(intent)
            }
        }
    }

}