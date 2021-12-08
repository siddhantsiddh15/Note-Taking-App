package com.nicheclout;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    //define column names
    // two columns are needed. One is note titke and other is note description
    // id must be given to each note like 1, 2 for recognition

    // so I need to define an id column as well with note title and note description


    // you must write @PrimaryKey(autogrnerate true) before primary key column
    @PrimaryKey(autoGenerate = true)
    public int id;


    public String title;
    public String description;

    // create constructor


    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // I didn't choose id for constructor because id will be generated automatically

    //let's write getter methods for variable


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    // we will also write setter code

    public void setId(int id) {
        this.id = id;
    }
}
