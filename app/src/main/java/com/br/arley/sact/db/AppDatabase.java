package com.br.arley.sact.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.br.arley.sact.dao.AvaliationDao;
import com.br.arley.sact.model.AuthData;
import com.br.arley.sact.model.Avaliation;

//@Database(entities = {Avaliation.class, AuthData.class}, version = 1)
public abstract class AppDatabase/* extends RoomDatabase */{

    //create database instance
    private static AppDatabase database;

    //define database name
    private static String DATABASE_NAME = "database";

    /*public synchronized static AppDatabase getInstance(Context context){

        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }

        return database;
    }*/

    public abstract AvaliationDao avaliationDao();


}