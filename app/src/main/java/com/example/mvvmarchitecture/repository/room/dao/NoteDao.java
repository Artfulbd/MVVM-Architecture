package com.example.mvvmarchitecture.repository.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvmarchitecture.repository.room.entity.Note;

import java.util.List;

// this interface is to access the database
@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    /*
     * Room doesn't have all possible annotation for database except "Insert", "Update" and "Delete"
     * so if needed, we can make our own query as given bellow
     */

    @Query("DELETE FROM NOTE_TABLE")  // nothing specified after Delete, so table will be cleaned
    void deleteAllNotes();

    // By doing this, LiveData will start observing this, any kind of change can be tracked.
    @Query("Select * from NOTE_TABLE order by priority desc")
    LiveData<List<Note>> getAllNotes();

}
