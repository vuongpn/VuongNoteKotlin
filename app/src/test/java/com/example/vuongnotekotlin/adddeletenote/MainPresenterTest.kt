package com.example.vuongnotekotlin.adddeletenote

import MainContract
import com.example.vuongnotekotlin.model.Note
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainPresenterTest {
    @Mock
    private lateinit var view: MainContract.View
    private lateinit var presenter: MainPresenter
    private lateinit var model: MainModel
    private var note = Note(1L, "abc", "27/08/1997")
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view)
        model = MainModel()
    }

    @Test
    fun onCreate() {
        presenter.onCreate()
    }

    @Test
    fun addNoteNull() {
        val a = presenter.addNote("")
        Assert.assertEquals(a, view.showError("Chưa nhập..."))
    }

    @Test
    fun addNoteNotNull() {
        val b = presenter.addNote("abc")
        Assert.assertEquals(b, view.clearEdt())
    }

    @Test
    fun onSuccess() {
        presenter.onSuccess()
    }

    @Test
    fun onFailure() {
        presenter.onFailure()
    }

    @Test
    fun onNoteDeleteClick() {
        presenter.onNoteDeleteClicked(note)
    }
}