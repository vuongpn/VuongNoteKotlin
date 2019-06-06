package com.example.vuongnotekotlin.adddeletenote

import com.example.vuongnotekotlin.adapters.NotesAdapter
import com.example.vuongnotekotlin.model.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainPresenter internal constructor(private val mView: MainContract.View) : NotesAdapter.NoteDeleteListener, MainContract.Presenter {

    private val mDatabase = MainModel()
    private val simpleDateFormat = SimpleDateFormat("dd/MM/yyy hh:mm a", Locale.US)

    internal fun onCreate() {
        mView.setAdapter(mDatabase.notes)
    }

    internal fun addNote(note: String) {
        if (note.isEmpty()) {
            mView.showError("Chưa nhập...")
        } else {
            mDatabase.addNote(Note(System.currentTimeMillis(), note, simpleDateFormat.format(Date())))
            mView.setAdapter(mDatabase.notes)
            mView.clearEdt()
        }
    }

    override fun onSuccess() {
    }

    override fun onFailure() {
    }

    override fun onNoteDeleteClicked(note: Note) {
        mDatabase.removeNote(note)
    }
}
