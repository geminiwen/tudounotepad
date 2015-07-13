package org.lifefortheorc.tudounotepad.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by geminiwen on 15/7/2.
 */
@Table(name = "Note")
public class NoteModel extends BaseModel {

    @Column(name = "content")
    private String content;

    @Column(name = "time")
    private long time;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public static List<NoteModel> queryNoteList() {
        return new Select().from(NoteModel.class).execute();
    }

    public static NoteModel queryById(long id) {
        return new Select().from(NoteModel.class)
                           .where("Id = ?", id).executeSingle();
    }

    public static NoteModel deleteById(long id) {
        return new Delete().from(NoteModel.class).where("Id = ?", id).executeSingle();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeLong(this.time);
    }

    public NoteModel() {
    }

    protected NoteModel(Parcel in) {
        this.content = in.readString();
        this.time = in.readLong();
    }

    public static final Parcelable.Creator<NoteModel> CREATOR = new Parcelable.Creator<NoteModel>() {
        public NoteModel createFromParcel(Parcel source) {
            return new NoteModel(source);
        }

        public NoteModel[] newArray(int size) {
            return new NoteModel[size];
        }
    };
}
