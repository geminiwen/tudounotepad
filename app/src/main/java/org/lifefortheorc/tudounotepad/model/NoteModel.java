package org.lifefortheorc.tudounotepad.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by geminiwen on 15/7/2.
 */
public class NoteModel extends BaseModel {

    private int id;
    private String title;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.content);
    }

    public NoteModel() {
    }

    protected NoteModel(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.content = in.readString();
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
