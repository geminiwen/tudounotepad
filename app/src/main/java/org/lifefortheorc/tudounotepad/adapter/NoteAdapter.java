package org.lifefortheorc.tudounotepad.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.lifefortheorc.tudounotepad.R;
import org.lifefortheorc.tudounotepad.activity.EditActivity;
import org.lifefortheorc.tudounotepad.model.NoteModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by geminiwen on 15/7/2.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

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
        View view = layoutInflater.inflate(R.layout.item_note, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        NoteModel note = noteList.get(position);

        String content = note.getContent();
        long time = note.getTime();

        viewHolder.mTextViewContent.setText(content);
        viewHolder.mTextViewTitle.setText(dateFormat.format(time));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.tv_title)
        public TextView mTextViewTitle;
        @Bind(R.id.tv_content)
        public TextView mTextViewContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            NoteModel note = noteList.get(position);


            long id = note.getId();
            Intent intent = new Intent(mContext, EditActivity.class);
            intent.putExtra("id", id);
            mContext.startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation((Activity)mContext,
                    v, "content").toBundle());
        }
    }

}
