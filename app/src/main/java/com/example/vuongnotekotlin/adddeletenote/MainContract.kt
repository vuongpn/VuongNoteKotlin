import com.example.vuongnotekotlin.model.Note

interface MainContract {

    interface View {

        fun showError(msg: String)

        fun setAdapter(notes: List<Note>)

        fun clearEdt()
    }

    interface Model {

        val notes: List<Note>

        fun addNote(note: Note)

        fun removeNote(note: Note)

        companion object {
            const val NOTES_LIST = "notes_list"
        }
    }

    interface Presenter{
        fun onSuccess()
        fun onFailure()
    }

}
