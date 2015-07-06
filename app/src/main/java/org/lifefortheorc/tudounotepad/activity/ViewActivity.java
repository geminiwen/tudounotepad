package org.lifefortheorc.tudounotepad.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import org.lifefortheorc.tudounotepad.R;
import org.lifefortheorc.tudounotepad.model.NoteModel;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by geminiwen on 15/7/6.
 */
public class ViewActivity extends Activity {

    @Bind(R.id.tv_content)
    public TextView mTextViewContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        ButterKnife.bind(this);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mTextViewContent.setTransitionName("content");

        Intent intent = getIntent();
        long id = intent.getLongExtra("id", -1);

        if (id == -1) {
            this.finish();
            return;
        }

        NoteModel note = NoteModel.queryById(id);
        String content = note.getContent();
        mTextViewContent.setText(content);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home: {
                this.finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
