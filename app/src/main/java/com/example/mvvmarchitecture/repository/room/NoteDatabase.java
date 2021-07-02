package com.example.mvvmarchitecture.repository.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mvvmarchitecture.repository.room.dao.NoteDao;
import com.example.mvvmarchitecture.repository.room.entity.Note;

import org.jetbrains.annotations.NotNull;

/*
 * For multiple tables
 * @Database(entities = {table1.class, table2.class, ... , tableN.class}, version = 1)
 * MUST SPECIFY DATABASE VERSION
 * If we change database, we must update "version"
 */
@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    private static final String DB_NAME = "note_database";

    // singleton pattern
    private static NoteDatabase instance;

    // we don't need to provide body for this, Room will take care of this
    // getInstance() will make it's body
    public abstract NoteDao noteDao();

    /*
     * creating database (singleton)
     * synchronized means one one thread at a time can access this method,
     * that means we cannot accidentally create this database multiple time
     */
    public static synchronized NoteDatabase getInstance(Context context){
        if(instance == null){
            // as this class is abstract, we cannot call it by new, so let it done by room
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()   //rollback if version don't matches
                    .addCallback(roomCallBack)    // Room database instance creation
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        // it will be call first time when database is being created
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void>{
        private NoteDao note_dao;

        private PopulateDBAsyncTask(NoteDatabase db){
            // this will be possible cuz, it will be called after DB gets created
            this.note_dao = db.noteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            note_dao.insert(new Note("Note1", "Description1",1));
            note_dao.insert(new Note("Note2", "Description2",2));
            note_dao.insert(new Note("Note3", "Description3",3));
            note_dao.insert(new Note("Note4", "Description4",4));
            return null;
        }
    }
}
