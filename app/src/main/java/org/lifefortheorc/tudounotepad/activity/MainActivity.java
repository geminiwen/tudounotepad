package org.lifefortheorc.tudounotepad.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewOutlineProvider;
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
        Intent intent = getIntent();
        handleSend(intent);
    }

    private void handleSend(Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_SEND)) {
            String content = intent.getStringExtra(Intent.EXTRA_TEXT);
            long time = System.currentTimeMillis();
            NoteModel note = new NoteModel();
            note.setContent(content);
            note.setTime(time);
            note.save();
        }
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        mNoteAdapter = new NoteAdapter(this);
        mRecyclerNote.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerNote.setAdapter(mNoteAdapter);

        mRecyclerNote.setEmptyView(mTextViewEmpty);

        ItemTouchHelper swipeToDismissTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                // callback for drag-n-drop, false to skip this feature
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // callback for swipe to dismiss, removing item from data and adapter
                mNoteAdapter.remove(viewHolder.getAdapterPosition());
                mNoteAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        swipeToDismissTouchHelper.attachToRecyclerView(mRecyclerNote);


        mAddBtn.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int width = view.getWidth();
                int height = view.getHeight();
                outline.setOval(0, 0, width, height);
            }
        });
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
