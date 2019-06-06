package com.example.vuongnotekotlin.adddeletenote

import com.example.vuongnotekotlin.model.Note
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainModelTest {
    private lateinit var model:MainModel
    @Mock private lateinit var presenter: MainPresenter
    private var note=Note(1,"1","01/01/2019")
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        model=MainModel()
    }

    @Test
    fun getNotes() {
        model.notes
    }

    @Test
    fun addNote() {
        model.addNote(note)
    }

    @Test
    fun removeNote() {
        model.removeNote(note)
    }
}