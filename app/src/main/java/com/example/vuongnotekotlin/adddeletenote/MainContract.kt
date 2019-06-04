import com.example.vuongnotekotlin.model.Note

interface MainContract {

    interface view {

        fun showError(msg: String)

        fun setAdapter(notes: List<Note>)

        fun clearEdt()

    }

    interface model {

        val notes: List<Note>

        fun addNote(note: Note)

        fun removeNote(note: Note)

        companion object {
            val NOTES_LIST = "notes_list"
        }

    }

    interface Presenter

}
