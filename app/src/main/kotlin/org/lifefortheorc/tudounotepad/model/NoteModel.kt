package org.lifefortheorc.tudounotepad.model

import android.util.Log
import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.activeandroid.query.Delete
import com.activeandroid.query.Select
import kotlin.platform.platformStatic

/**
 * Created by geminiwen on 15/7/13.
 */
Table(name = "Note")
public data class NoteModel() : BaseModel(){

    Column(name="content")
    var content:String? = null

    Column(name="time")
    var time:Long? = null

    constructor(content:String, time:Long): this() {
        this.content = content
        this.time = time;
    }

    companion object {
        platformStatic fun queryNoteList(): List<NoteModel> {
            val modelList:List<NoteModel> = Select().from(javaClass<NoteModel>()).execute()
            return modelList
        }

        platformStatic fun queryById(id: Long): NoteModel {
            val model:NoteModel = Select().from(javaClass<NoteModel>()).where("Id = ?", id).executeSingle()
            return model;
        }

        platformStatic fun deleteById(id: Long): NoteModel {
            val model:NoteModel = Delete().from(javaClass<NoteModel>()).where("Id = ?", id).executeSingle()
            return model
        }
    }

}