package com.nicheclout;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDAO noteDAO;
    private LiveData<List<Note>> notes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDAO = database.noteDao();
        notes = noteDAO.getAllNotes(); // methods were declared in interface

        // now I can call methods that will use in the database in this section

    }

    public void insert(Note note){

        new InsertNoteAsyncTask(noteDAO).execute(note);

    }

    public void update(Note note){

        new UpdateNoteAsyncTask(noteDAO).execute(note);
    }
    public void delete(Note note){

        new DeleteNoteAsyncTask(noteDAO).execute(note);
    }
    public LiveData<List<Note>> getAllNotes(){

        //this method will return the note array we created above

        return notes;

    }
    // Room database will automatically perform the necessary operations for a live data as a backgroud trait which works in the background
    // we will create async tasks at the bottom of these particular processes

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDAO noteDAO;

        private InsertNoteAsyncTask(NoteDAO noteDAO){
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDAO.Insert(notes[0]);
            return null;
        }
    }




    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDAO noteDAO;

        private UpdateNoteAsyncTask(NoteDAO noteDAO){
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDAO.Update(notes[0]);
            return null;
        }
    }




    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDAO noteDAO;

        private DeleteNoteAsyncTask(NoteDAO noteDAO){
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDAO.Delete(notes[0]);
            return null;
        }
    }
}
