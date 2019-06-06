package com.example.vuongnotekotlin.adddeletenote

import MainContract
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import com.example.vuongnotekotlin.R
import com.example.vuongnotekotlin.adapters.NotesAdapter
import com.example.vuongnotekotlin.custom.LinearLayoutCustom
import com.example.vuongnotekotlin.model.Note
import java.util.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private var mBtnAdd: Button? = null
    private var mEdtNote: EditText? = null
    private var recyclerViewNote: RecyclerView? = null
    private val mPresenter = MainPresenter(this)
    private var mAdapter: NotesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBtnAdd = findViewById(R.id.btnAddNote)
        mEdtNote = findViewById(R.id.edtNote)
        recyclerViewNote = findViewById(R.id.recyclerViewNote)
        initListener()
        initRecyclerView()
        mPresenter.onCreate()
        MainModel.init(this)
    }

    private fun initListener() {
        mBtnAdd!!.setOnClickListener { mPresenter.addNote(mEdtNote!!.text.toString()) }
    }

    private fun initRecyclerView() {
        recyclerViewNote!!.layoutManager = LinearLayoutCustom(this)
        mAdapter = NotesAdapter(ArrayList(), mPresenter)
        recyclerViewNote!!.adapter = mAdapter
    }

    override fun showError(msg: String) {
    }

    override fun setAdapter(notes: List<Note>) {
        mAdapter!!.updateAdapter(notes)
    }

    override fun clearEdt() {
        mEdtNote!!.setText("")
    }
}
