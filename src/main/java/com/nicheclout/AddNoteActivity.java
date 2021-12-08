package com.nicheclout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nicheclout.notetake.R;

public class AddNoteActivity extends AppCompatActivity {

    EditText title;
    EditText description;

    Button cancel;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Note");  // used to change the title of the activity add note
        setContentView(R.layout.activity_add_note);

        title = findViewById(R.id.editTextTitle);
        description = findViewById(R.id.editTextdescription);
        cancel = findViewById(R.id.buttonCancel);
        save = findViewById(R.id.buttonSave);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(AddNoteActivity.this, "Nothing Saved", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveNote();

            }
        });
    }

    public void saveNote(){

        String noteTitle = title.getText().toString();
        String noteDescription = description.getText().toString();

        // need to add string to the database
        Intent intent = new Intent();
        intent.putExtra("noteTitle" ,noteTitle);
        intent.putExtra("noteDescription", noteDescription);
        setResult(RESULT_OK, intent);
        finish();

    }
}