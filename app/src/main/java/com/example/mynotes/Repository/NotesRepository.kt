package com.example.mynotes.Repository

import androidx.lifecycle.LiveData
import com.example.mynotes.Dao.NotesDao
import com.example.mynotes.Model.Notes

class NotesRepository(val dao: NotesDao) {

    //get
    fun getAllNotes(): LiveData<List<Notes>> {
        return dao.getNotes()
    }

    fun getHighNotes(): LiveData<List<Notes>> {
        return dao.getHighNotes()
    }

    fun getMediumNotes(): LiveData<List<Notes>> {
        return dao.getMediumNotes()
    }

    fun getLowNotes(): LiveData<List<Notes>> {
        return dao.getLowNotes()
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