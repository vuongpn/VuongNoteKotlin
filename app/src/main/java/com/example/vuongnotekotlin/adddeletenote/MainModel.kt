package com.example.vuongnotekotlin.adddeletenote



import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.example.vuongnotekotlin.model.Note
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList


class MainModel internal constructor() : MainContract.model {
    private val mPreferences: SharedPreferences?

    override val notes: MutableList<Note>
        get() {
            var notes = ArrayList<Note>()
            val noteListString = mPreferences!!.getString(MainContract.model.NOTES_LIST, "")
            if (!TextUtils.isEmpty(noteListString)) {
                notes = Gson().fromJson(noteListString, object : TypeToken<List<Note>>() {

                }.type)
            }
            return notes
        }

    init {
        this.mPreferences = MainModel.providePreferences()
    }

    override fun addNote(note: Note) {
        val dbNotes = notes
        dbNotes.add(0, note)
        val noteAsString = Gson().toJson(dbNotes)
        mPreferences!!.edit().putString(MainContract.model.NOTES_LIST, noteAsString).apply()
    }

    override fun removeNote(note: Note) {
        val dbNotes = notes
        for (i in 0 until dbNotes.size ) {
            if (dbNotes[i].id == note.id) {
                dbNotes.removeAt(i)
                break
            }
        }
        if (dbNotes.size > 0) {
            val noteAsString = Gson().toJson(dbNotes)
            mPreferences!!.edit().putString(MainContract.model.NOTES_LIST, noteAsString).apply()
        } else {
            mPreferences!!.edit().putString(MainContract.model.NOTES_LIST, "").apply()
        }
    }

    companion object {
        private val SHARED_PREFERENCES = "notePreferences"
        private var sPreferences: SharedPreferences? = null
        private fun providePreferences(): SharedPreferences? {
            return sPreferences
        }

        fun init(context: Context) {
            sPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        }
    }

}
