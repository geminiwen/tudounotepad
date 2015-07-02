package org.lifefortheorc.tudounotepad.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.lifefortheorc.tudounotepad.R;
import org.lifefortheorc.tudounotepad.model.NoteModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by geminiwen on 15/7/2.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private Context mContext;
    private List<NoteModel> noteList;

    public NoteAdapter(Context ctx) {
        this.noteList = new ArrayList<>();
        this.mContext = ctx;
    }

    public void setNoteList(List<NoteModel> noteList) {
        this.noteList.clear();
        this.noteList.addAll(noteList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_note, viewGroup);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_title)
        public TextView mTextViewTitle;
        @Bind(R.id.tv_content)
        public TextView mTextViewContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
