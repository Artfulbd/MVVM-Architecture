package com.example.mvvmarchitecture.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

import com.example.mvvmarchitecture.repository.room.NoteDatabase;
import com.example.mvvmarchitecture.repository.room.dao.NoteDao;
import com.example.mvvmarchitecture.repository.room.entity.Note;

import java.util.List;

public class Repository {
    private NoteDao note_dao;
    private LiveData<List<Note>> all_notes;

    public Repository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        note_dao = database.noteDao();
        all_notes = note_dao.getAllNotes();
    }

    public void insert(Note note){
        // letting NoteDao to execute query on a different thread
        new InsertNoteAsyncTask(note_dao).execute(note);
    }
    public void update(Note note){
        new UpdateNoteAsyncTask(note_dao).execute(note);
    }
    public void delete(Note note){
        new DeleteNoteAsyncTask(note_dao).execute(note);
    }
    public void deleteAllNote(){
        new DeleteAllNotesAsyncTask(note_dao).execute();
    }
    public LiveData<List<Note>> getAllNotes(){
        return all_notes;
    }

    // this AsyncTask will do job (defined on it) outside the main thread
    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao note_dao;
        // this class is static, so we cannot use repository class directly

        private InsertNoteAsyncTask(NoteDao noteDao){
            this.note_dao = noteDao;
        }
        // Object... obg" is simply an array of Object with not fixed length
        @Override
        protected Void doInBackground(Note... notes){
            // now do the query job here
            note_dao.insert(notes[0]);// one note so index zero
            return null;
        }
    }
    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao note_dao;
        // this class is static, so we cannot use repository class directly

        private UpdateNoteAsyncTask(NoteDao note_dao){
            this.note_dao = note_dao;
        }
        // Object... obg" is simply an array of Object with not fixed length
        @Override
        protected Void doInBackground(Note... notes){
            // now do the query job here
            note_dao.update(notes[0]);// one note so index zero
            return null;
        }
    }
    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao note_dao;
        // this class is static, so we cannot use repository class directly

        private DeleteNoteAsyncTask(NoteDao note_dao){
            this.note_dao = note_dao;
        }
        // Object... obg" is simply an array of Object with not fixed length
        @Override
        protected Void doInBackground(Note... notes){
            // now do the query job here
            note_dao.delete(notes[0]);// one note so index zero
            return null;
        }
    }
    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao note_dao;
        // this class is static, so we cannot use repository class directly

        private DeleteAllNotesAsyncTask(NoteDao note_dao){
            this.note_dao = note_dao;
        }
        // Object... obg" is simply an array of Object with not fixed length
        @Override
        protected Void doInBackground(Void... voids){
            // now do the query job here
            note_dao.deleteAllNotes();// one note so index zero
            return null;
        }
    }
}
