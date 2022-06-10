package com.lexmasterteam.notesapproomdb.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lexmasterteam.notesapproomdb.model.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNote(note:Note)

    @Query("SELECT * FROM note_table ORDER By id ASC")
    fun readAllData(): LiveData<List<Note>>

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("DELETE FROM note_table")
    suspend fun deleteAllNotes()
}