package com.nicheclout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nicheclout.notetake.R;

public class UpdateActivity extends AppCompatActivity {


    EditText title;
    EditText description;

    Button cancel;
    Button save;
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit Note");  // used to change the title of the activity add note
        setContentView(R.layout.activity_update);

        title = findViewById(R.id.editTextTitleUpdate);
        description = findViewById(R.id.editTextdescriptionUpdate);
        cancel = findViewById(R.id.buttonCancelUpdate);
        save = findViewById(R.id.buttonSaveUpdate);

        getData();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(UpdateActivity.this, "Nothing Updated", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateNote();

            }
        });
    }

    public void updateNote(){

        //to save the dited version of the notes of the databse, we must take the edited note from components and send it back to the main activity

        String titleLast = title.getText().toString();
        String descriptionLast = description.getText().toString();

        Intent i = new Intent();
        i.putExtra("titleLast", titleLast);
        i.putExtra("descriptionLast", descriptionLast);

        if(noteId != -1){
            i.putExtra("noteId", noteId);
            //if update = -1, update cannot be done
            setResult(RESULT_OK,i);;
            finish();

            // now we will write code in MainActivity to receive this data
        }
 }

    public void getData(){
        Intent i = getIntent();
        noteId = i.getIntExtra("id", -1);
        String noteTitle = i.getStringExtra("title");
        String noteDescription = i.getStringExtra("description");


        title.setText(noteTitle);
        description.setText(noteDescription);
    }
}