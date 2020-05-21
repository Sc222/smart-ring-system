package ru.sc222.smartringapp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LocationDao {
    @Query("SELECT * FROM `Location`")
    List<Location> getAll();

    @Insert
    void insertAll(Location... locations);

}
