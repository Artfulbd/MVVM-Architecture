package com.example.mvvmarchitecture.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmarchitecture.repository.Repository;
import com.example.mvvmarchitecture.repository.room.entity.Note;

import java.util.List;


public class NoteViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Note>> all_notes;

    public NoteViewModel(@NonNull Application application){
        super(application);
        repository = new Repository(application);
        all_notes = repository.getAllNotes();
    }

    public void insert(Note note){
        repository.insert(note);
    }
    public void update(Note note){
        repository.update(note);
    }
    public void delete(Note note){
        repository.delete(note);
    }
    public void deleteAllNote(){
        repository.deleteAllNote();
    }
    public LiveData<List<Note>> getAllNotes(){
        return all_notes;
    }
}
