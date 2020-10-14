package com.br.arley.sact.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.br.arley.sact.model.Avaliation;

import java.util.List;

@Dao
public interface AvaliationDao {
    @Insert
    void insertAll(Avaliation... avaliation);

    @Delete
    void delete(Avaliation avaliation);

    @Query("SELECT * FROM avaliation")
    List<Avaliation> getAllAvaliations();

}

