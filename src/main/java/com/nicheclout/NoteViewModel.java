package com.nicheclout;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {


    private NoteRepository repository;
    private LiveData<List<Note>> notes;
    public NoteViewModel(@NonNull @NotNull Application application) {
        super(application);

        repository = new NoteRepository(application);
        notes = repository.getAllNotes();
    }

    public void insert(Note note){
        repository.insert(note);
    }

    public void update(Note note){
        repository.update(note);;
    }

    public void delete(Note note){
        repository.delete(note);;
    }
   //let's call the get all method

    public LiveData<List<Note>> getAllNotes(){

        return notes;
    }

    // we should show this view model as a reference in the activity
}
