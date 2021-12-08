package com.nicheclout;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.jetbrains.annotations.NotNull;

@Database(entities = Note.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    // defining some element
    private static NoteDatabase instance;    // will write an object from NoteDatabase and will use this database object everywhere in the application


    // I defined this object to be static because that enables it to access it from anywhere in the application

    public abstract NoteDAO noteDao(); // this method has not type and body because rest of the room will handle it.

    public static synchronized NoteDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext()
            , NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    // code tells how to handle the database we created here

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){


        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // run the async method to populate the dtabase by below codes
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{


        private NoteDAO noteDAO;

        private PopulateDbAsyncTask(NoteDatabase database){
            noteDAO = database.noteDao();
            // now we can create a database
        }

        @Override
        protected Void doInBackground(Void... voids) {

            noteDAO.Insert(new Note("Title 1", "Description 1"));
            noteDAO.Insert(new Note("Title 2", "Description 2"));
            noteDAO.Insert(new Note("Title 3", "Description 3"));
            noteDAO.Insert(new Note("Title 4", "Description 4"));
            noteDAO.Insert(new Note("Title 5", "Description 5"));
            noteDAO.Insert(new Note("Title 6", "Description 6"));

            return null;
        }
    }
}
