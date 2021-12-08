package com.nicheclout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nicheclout.notetake.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter  extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {


    private List<Note> notes = new ArrayList<>();

    private onItemClickListener listener;      // defined the listener object as one of the main elements of this class


    @NonNull
    @NotNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        // I will describe the specially designed layout named note_item in this method

        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.note_item, parent, false);

        return new NoteHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NoteHolder holder, int position) {
// In this method we transfer the data from each Java object to the note holder
        // We will also write the incoming data in textViews

        // determining the position of the note object
        Note currentNote = notes.get(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    // adding another method here

    public void setNotes(List<Note> notes){
        // in this method we will check changes in live data
        this.notes = notes;
        notifyDataSetChanged(); // this method will notify if there is a change in database


    }

    // to know the position of the note sent
    public Note getNotes(int position){
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle;
        TextView textViewDescription;

        public NoteHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(notes.get(position));
                    }
                }
            });

        }
    }

    //add a click listener to the recycler view
    public interface  onItemClickListener{
        void onItemClick(Note note);
    }
    public void setOnItemClickListener(onItemClickListener listner){

        // her ewe are going to use the listener object that we created from the on click listener item interface
    this.listener = listner;

    }
}
