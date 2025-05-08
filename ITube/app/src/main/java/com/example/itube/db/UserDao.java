package com.example.itube.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    User login(String username, String password);

    @Insert
    void insert(User user);
} 