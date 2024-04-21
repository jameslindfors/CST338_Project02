package com.cst338.project02;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insert(User... user);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE)
    List<User> getUserLogs();

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE username = :username AND password = :password")
    List<User> loginUser(String username, String password);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE username = :username")
    List<User> getUsername(String username);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE id = :id")
    List<User> getId(String id);

    @Query("DELETE from " + AppDatabase.USER_TABLE)
    void deleteAll();
}
