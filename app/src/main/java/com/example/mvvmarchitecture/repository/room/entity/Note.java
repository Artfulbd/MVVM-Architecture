package com.example.mvvmarchitecture.repository.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * will create sqlite object
 * on compile time, it will generate al necessary code for generating this DB stuff
 */

@Entity(tableName = "note_table")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int note_id;
    private String title;
    private String description;

    // @ColumnInfo(name="some_name")  // setting column name by own, else it will set as var name
    private int priority;

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public int getNote_id() {
        return note_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
