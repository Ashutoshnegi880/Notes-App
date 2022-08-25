package com.example.mynotes.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mynotes.Model.Notes

@Dao
interface NotesDao {
    //GET
    @Query("SELECT * FROM Notes")
    fun getNotes(): LiveData<List<Notes>>

    //Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    //Delete
    @Query("DELETE FROM Notes WHERE id =:id")
    fun deleteNotes(id: Int)

    //Update
    @Update
    fun updateNotes(notes: Notes)

}