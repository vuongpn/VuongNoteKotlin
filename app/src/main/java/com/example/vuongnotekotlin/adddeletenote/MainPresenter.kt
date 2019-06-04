package com.example.vuongnotekotlin.adddeletenote

import android.text.TextUtils
import com.example.vuongnotekotlin.adapters.NotesAdapter
import com.example.vuongnotekotlin.model.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainPresenter internal constructor(private val mView: MainContract.view) : NotesAdapter.NoteDeleteListener, MainContract.Presenter {
    private val mDatabase = MainModel()
    private val simpleDateFormat = SimpleDateFormat("dd/MM/yyy hh:mm a", Locale.US)

    internal fun onCreate() {
        mView.setAdapter(mDatabase.notes)
    }

    internal fun addNote(note: String) {
        if (TextUtils.isEmpty(note)) {
            mView.showError("Chưa nhập...")
        } else {
            mDatabase.addNote(Note(System.currentTimeMillis(), note, simpleDateFormat.format(Date())))
            mView.setAdapter(mDatabase.notes)
            mView.clearEdt()
        }
    }

    override fun onNoteDeleteClicked(note: Note) {
        mDatabase.removeNote(note)
    }
}
