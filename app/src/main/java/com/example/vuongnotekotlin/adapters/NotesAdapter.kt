package com.example.vuongnotekotlin.adapters

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.vuongnotekotlin.R
import com.example.vuongnotekotlin.model.Note
import java.util.Random


class NotesAdapter(private val noteList: MutableList<Note>, private val noteDeleteListener: NoteDeleteListener) : RecyclerView.Adapter<NotesAdapter.NoteHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        return NoteHolder(LayoutInflater.from(parent.context).inflate(R.layout.notes, parent, false))
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val item = noteList[position]
        holder.tvNote.text = item.note
        holder.tvDate.text = item.date
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.viewIndicator.setBackgroundColor(color)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun updateAdapter(notesList: List<Note>) {
        noteList.clear()
        noteList.addAll(notesList)
        notifyDataSetChanged()
    }

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val viewIndicator: View
         val tvNote: TextView
         val tvDate: TextView
         val imgDelete: ImageView

        init {
            setIsRecyclable(false)
            viewIndicator = itemView.findViewById(R.id.viewIndicator)
            tvNote = itemView.findViewById(R.id.tvNote)
            tvDate = itemView.findViewById(R.id.tvDate)
            imgDelete = itemView.findViewById(R.id.imgDelete)
            imgDelete.setOnClickListener {
                noteDeleteListener.onNoteDeleteClicked(noteList[adapterPosition])
                noteList.removeAt(adapterPosition)
                notifyDataSetChanged()
            }
        }

    }

    interface NoteDeleteListener {
        fun onNoteDeleteClicked(note: Note)
    }

}
