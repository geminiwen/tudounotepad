package org.lifefortheorc.tudounotepad.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import org.lifefortheorc.tudounotepad.R;
import org.lifefortheorc.tudounotepad.model.NoteModel;
import org.lifefortheorc.tudounotepad.widget.TudouToast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by geminiwen on 15/7/2.
 */
public class EditActivity extends Activity {

    @Bind(R.id.et_content)
    public EditText mEditTextContent;

    private NoteModel mNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mEditTextContent.setTransitionName("content");

        Intent intent = getIntent();
        long id = intent.getLongExtra("id", -1l);
        if (id != -1l) {
            mNote = NoteModel.queryById(id);
            String content = mNote.getContent();
            mEditTextContent.setText(content);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home: {
                this.finish();
                return true;
            }
            case R.id.save: {
                String content = mEditTextContent.getText().toString();

                if (TextUtils.isEmpty(content)) {
                    TudouToast.show(R.string.no_content_no_save);
                    return true;
                }

                if (mNote == null) {
                    mNote = new NoteModel();
                }
                mNote.setTime(System.currentTimeMillis());
                mNote.setContent(content);
                mNote.save();

                if(TextUtils.isEmpty(content)) {
                    mNote.delete();
                }

                TudouToast.show(R.string.toast_save_success);
                this.finish();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
