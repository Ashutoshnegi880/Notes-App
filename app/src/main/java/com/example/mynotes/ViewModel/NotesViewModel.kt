package com.example.mynotes.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mynotes.Database.NotesDatabase.NotesDatabase
import com.example.mynotes.Model.Notes
import com.example.mynotes.Repository.NotesRepository
import org.w3c.dom.Node

class NotesViewModel(application: Application):AndroidViewModel(application) {

    val repository:NotesRepository
    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }

    fun getNotes():LiveData<List<Notes>>{
        return repository.getAllNotes()
    }

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }

    fun deleteNote(id: Int){
        repository.deleteNotes(id)
    }

    fun updateNote(notes: Notes){
        repository.updateNotes(notes)
    }


}