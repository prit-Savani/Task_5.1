package com.example.itube.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface PlaylistDao {
    @Query("SELECT * FROM PlaylistItem WHERE userId = :userId")
    List<PlaylistItem> getPlaylist(int userId);

    @Insert
    void insert(PlaylistItem item);
} 