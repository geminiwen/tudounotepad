package org.lifefortheorc.tudounotepad.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import org.lifefortheorc.tudounotepad.R;
import org.lifefortheorc.tudounotepad.adapter.NoteAdapter;
import org.lifefortheorc.tudounotepad.model.NoteModel;
import org.lifefortheorc.tudounotepad.widget.EmptyRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 这是应用主界面。
 */
public class MainActivity extends Activity {

    @Bind(R.id.fab_add)
    public FloatingActionButton mAddBtn;
    @Bind(R.id.recycler_note)
    public EmptyRecyclerView mRecyclerNote;
    @Bind(R.id.tv_empty)
    public TextView mTextViewEmpty;

    private NoteAdapter mNoteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        mNoteAdapter = new NoteAdapter(this);
        mRecyclerNote.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerNote.setAdapter(mNoteAdapter);

        mRecyclerNote.setEmptyView(mTextViewEmpty);


    }

    @Override
    protected void onResume() {
        super.onResume();
        List<NoteModel> noteList = NoteModel.queryNoteList();
        mNoteAdapter.setNoteList(noteList);
        mNoteAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.fab_add)
    public void onClick(View v) {
        if (v == mAddBtn) {
            Intent intent = new Intent(this, EditActivity.class);
            startActivity(intent);
        }
    }
}
