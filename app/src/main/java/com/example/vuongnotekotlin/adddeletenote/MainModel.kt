package com.example.vuongnotekotlin.adddeletenote

import MainContract
import android.content.Context
import android.content.SharedPreferences
import com.example.vuongnotekotlin.model.Note
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class MainModel internal constructor() : MainContract.Model {
    private val mPreferences: SharedPreferences?

    override val notes: MutableList<Note>
        get() {
            var notes = ArrayList<Note>()
            val noteListString = mPreferences?.getString(MainContract.Model.NOTES_LIST, "")
            if (noteListString != null) {
                if (noteListString.isNotEmpty()) {
                    notes = Gson().fromJson(noteListString, object : TypeToken<List<Note>>() {
                    }.type)
                }
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
        mPreferences?.edit()?.putString(MainContract.Model.NOTES_LIST, noteAsString)?.apply()
    }

    override fun removeNote(note: Note) {
        val dbNotes = notes
        for (i in 0 until dbNotes.size) {
            if (dbNotes[i].id == note.id) {
                dbNotes.removeAt(i)
                break
            }
        }
        if (dbNotes.size > 0) {
            val noteAsString = Gson().toJson(dbNotes)
            mPreferences?.edit()?.putString(MainContract.Model.NOTES_LIST, noteAsString)?.apply()
        } else {
            mPreferences?.edit()?.putString(MainContract.Model.NOTES_LIST, "")?.apply()
        }
    }

    companion object {
        private const val SHARED_PREFERENCES = "notePreferences"
        private var sPreferences: SharedPreferences? = null
        private fun providePreferences(): SharedPreferences? {
            return sPreferences
        }

        fun init(context: Context) {
            sPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        }
    }
}
