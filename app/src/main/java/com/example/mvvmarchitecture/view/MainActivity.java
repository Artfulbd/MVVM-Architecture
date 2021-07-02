package com.example.mvvmarchitecture.view;

import android.os.Bundle;

import com.example.mvvmarchitecture.R;
import com.example.mvvmarchitecture.adapter.NoteAdapter;
import com.example.mvvmarchitecture.repository.room.entity.Note;
import com.example.mvvmarchitecture.viewModel.NoteViewModel;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmarchitecture.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setHasFixedSize(true); // do it if we know recycler view size will not change

        final NoteAdapter adapter = new NoteAdapter();
        recycler_view.setAdapter(adapter);

        // android system will destroy this viewMode if this activity gets closed
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        // getAllNotes will return a live data, so will call observe, which is a live data method
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {   // if we rotate screen or any change on Note table, this method will get called again
                // will update the view everytime with new list of notes and refresh it's state automatically
                adapter.setNotes(notes);
            }
        });


    }


}