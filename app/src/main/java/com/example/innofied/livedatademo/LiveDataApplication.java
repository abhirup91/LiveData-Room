package com.example.innofied.livedatademo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
@Database(entities = {Person.class},version = 1)
public abstract class LiveDataApplication extends RoomDatabase {

    private static volatile LiveDataApplication INSTANCE;

    public abstract PersonDao personDao();

    public static LiveDataApplication getInstance(Context context) {
        if(INSTANCE == null) {
            synchronized(LiveDataApplication.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LiveDataApplication.class, "room_application_db.db").build();
                }
            }
        }
        return INSTANCE;
    }
}
