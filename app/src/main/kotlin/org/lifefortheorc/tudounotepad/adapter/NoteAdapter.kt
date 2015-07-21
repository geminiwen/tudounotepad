package org.lifefortheorc.tudounotepad.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.Bind
import butterknife.ButterKnife
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import org.lifefortheorc.tudounotepad.R
import org.lifefortheorc.tudounotepad.activity.EditActivity
import org.lifefortheorc.tudounotepad.activity.ViewActivity
import org.lifefortheorc.tudounotepad.model.NoteModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.platform.platformStatic

/**
 * Created by geminiwen on 15/7/13.
 */

public class NoteAdapter(val context: Context): RecyclerSwipeAdapter<NoteAdapter.ViewHolder> () {

    companion object {
        platformStatic val dateFormat = SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    }
    
    val noteList: MutableList<NoteModel>

    init {
        noteList = ArrayList<NoteModel>()
    }

    override fun getSwipeLayoutResourceId(p0: Int): Int {
        return R.id.swipe
    }

    override fun getItemCount(): Int {
        return noteList.size()
    }

    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): ViewHolder? {
        val layoutInflater = LayoutInflater.from(context);
        val view = layoutInflater.inflate(R.layout.item_note, p0, false);
        return ViewHolder(view);
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, p1: Int) {

        val note = noteList.get(p1);

        val content = note.content;
        val time = note.time;

        mItemManger.bindView(viewHolder?.mSwipeLayout, p1);
        viewHolder?.mTextViewContent?.setText(content);
        viewHolder?.mTextViewTitle?.setText(dateFormat.format(time));
    }

    public fun setNoteList(list: List<NoteModel>) {
        noteList.clear()
        noteList.addAll(list)
    }

    public inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        public var mTextViewTitle: TextView
        public var mTextViewContent: TextView
        public var mLayoutContent: View
        public var mViewEditAction: View
        public var mViewDeleteAction: View
        public var mSwipeLayout: SwipeLayout

        init {
            mTextViewTitle = itemView.findViewById(R.id.tv_title) as TextView
            mTextViewContent = itemView.findViewById(R.id.tv_content) as TextView
            mLayoutContent = itemView.findViewById(R.id.layout_content)
            mViewEditAction = itemView.findViewById(R.id.tv_edit)
            mViewDeleteAction = itemView.findViewById(R.id.tv_delete)
            mSwipeLayout = itemView.findViewById(R.id.swipe) as SwipeLayout

            mLayoutContent.setOnClickListener(this)
            mViewEditAction.setOnClickListener(this)
            mViewDeleteAction.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val id = v.getId()
            val position = this.getAdapterPosition()
            val note = noteList.get(position)
            when (id) {
                R.id.layout_content -> {
                    val noteId = note.getId()!!
                    val intent = Intent(context, javaClass<ViewActivity>())
                    intent.putExtra("id", noteId)
                    context.startActivity(intent)
                }
                R.id.tv_edit -> {
                    val noteId = note.getId()!!
                    val intent = Intent(context, javaClass<EditActivity>())
                    intent.putExtra("id", noteId)
                    context.startActivity(intent)
                }
                R.id.tv_delete -> {
                    note.delete()
                    noteList.remove(note)
                    notifyItemRemoved(position)
                }
            }

        }

    }
}