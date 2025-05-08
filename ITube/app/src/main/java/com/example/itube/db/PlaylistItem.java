package com.example.itube.db;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PlaylistItem {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int userId;
    public String videoUrl;
} 