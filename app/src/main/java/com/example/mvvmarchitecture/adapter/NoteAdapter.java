package com.example.mvvmarchitecture.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmarchitecture.R;
import com.example.mvvmarchitecture.repository.room.entity.Note;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    /* this var will be filled by LiveData, but for safety we put an ArrayList here
     * so that it will never be null, even if it gets accessed before Live data initialized it.
     */
    private List<Note> notes = new ArrayList<>();


    @NotNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View item_view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(item_view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NoteHolder holder, int position) {
        Note current_note = notes.get(position);
        holder._text_view_title.setText(current_note.getTitle());
        holder._text_view_priority.setText(String.valueOf(current_note.getPriority()));
        holder._text_view_description.setText(current_note.getDescription());
    }

    // how many items should it display on the recycler view
    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }

    // this inner class will hold our views on recycler view items
    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView _text_view_title;
        private TextView _text_view_priority;
        private TextView _text_view_description;

        public NoteHolder(View item_view){
            super(item_view);
            _text_view_title = item_view.findViewById(R.id.text_view_title);
            _text_view_priority = item_view.findViewById(R.id.text_view_priority);
            _text_view_description = item_view.findViewById(R.id.text_view_description);

        }


    }
}
