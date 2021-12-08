package com.nicheclout;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    void Insert(Note note);

    @Update
    void Update(Note note);

    @Delete
    void Delete(Note note);

    @Query( "SELECT * FROM note_table ORDER BY id ASC")
    LiveData<List<Note>> getAllNotes();          //live data acrchitectural components will be added to beginning in this line
                                      // It is going to observe the live database and then reflect it to recycler view if there is a change

}
