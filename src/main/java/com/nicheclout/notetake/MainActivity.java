package com.nicheclout.notetake;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.nicheclout.AddNoteActivity;
import com.nicheclout.Note;
import com.nicheclout.NoteAdapter;
import com.nicheclout.NoteViewModel;
import com.nicheclout.UpdateActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    //defining the viewModel here
    // create an object from ViewModel class here
    private NoteViewModel  noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to show the menu in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_main);

        //setting us recycler View after writing codes in Adapter class
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //now we create an object from the note Adapter class
        NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);
        // now we will update recycler view from onChanged method below


        noteViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(NoteViewModel.class);

        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {

                //this method will observe the changes
                // here we will update the Recycler View
                adapter.setNotes(notes);


            }
        });  // observe keeps an eye on the live data


        //TO DELETE THE NOTES

        new ItemTouchHelper(new ItemTouchHelper
                .SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {

                noteViewModel.delete(adapter.getNotes(viewHolder.getAdapterPosition()));  // will determine position of element to delete
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new NoteAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Note note) {

                Intent i = new Intent(MainActivity.this, UpdateActivity.class);
                i.putExtra("id", note.getId());
                i.putExtra("title", note.getTitle());
                i.putExtra("description", note.getDescription());

                startActivityForResult(i, 2);

            }
        });
    }

    //done after writing codes for menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.new_menu, menu);;
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.top_menu:
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivityForResult(intent, 1 );; // I will open the new activity with this 1 code.
                // When I will turn off take note activity I will the request code to the on activity result page
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }


    //getting data from note activity in this method


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK){

            // now returning to the add note activity immediately, we sent the result ok code with the set result method
            String title = data.getStringExtra("noteTitle");
            String description = data.getStringExtra("noteDescription");

            Note note = new Note(title, description);
            noteViewModel.insert(note);

            // We should make main activity, the parent activity of the add node activity so that the intent method works
        }
        else if(requestCode == 2 && resultCode == RESULT_OK){

            String title = data.getStringExtra("titleLast");
            String description = data.getStringExtra("descriptionLast");
            int id = data.getIntExtra("noteId", -1);

            Note note = new Note(title, description);
            note.setId(id);
            noteViewModel.update(note);
        }
    }
}