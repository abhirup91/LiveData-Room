package com.example.innofied.livedatademo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;
@Dao
public interface PersonDao {
    @Query("SELECT * FROM PERSONS")
    LiveData<List<Person>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Person person);

//    @Query("DELETE FROM PERSONS WHERE NAME= :name")
//    void delete(Person person);
}
