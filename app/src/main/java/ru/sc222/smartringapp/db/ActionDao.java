package ru.sc222.smartringapp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ActionDao {
    @Query("SELECT * FROM `Action`")
    List<Action> getAll();

    @Insert
    void insertAll(Action... actions);
}
