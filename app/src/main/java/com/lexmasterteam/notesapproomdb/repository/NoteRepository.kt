package com.lexmasterteam.notesapproomdb.repository

import androidx.lifecycle.LiveData
import com.lexmasterteam.notesapproomdb.data.NoteDao
import com.lexmasterteam.notesapproomdb.model.Note

class NoteRepository(private val noteDao: NoteDao) {
    val readAllData: LiveData<List<Note>> = noteDao.readAllData()

    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }
    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }
    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }
    suspend fun deleteAllNotes(){
        noteDao.deleteAllNotes()
    }
}