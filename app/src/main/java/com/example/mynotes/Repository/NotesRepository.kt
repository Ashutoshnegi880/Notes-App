package com.example.mynotes.Repository

import androidx.lifecycle.LiveData
import com.example.mynotes.Dao.NotesDao
import com.example.mynotes.Model.Notes

class NotesRepository(val dao: NotesDao) {

    //get
    fun getAllNotes(): LiveData<List<Notes>> {
        return dao.getNotes()
    }

    //insert
    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }

    //delete
    fun deleteNotes(id:Int){
        dao.deleteNotes(id)
    }

    //update
    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }
}