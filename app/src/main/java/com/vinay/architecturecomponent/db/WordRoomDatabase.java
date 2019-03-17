package com.vinay.architecturecomponent.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.vinay.architecturecomponent.db.dao.WordDao;
import com.vinay.architecturecomponent.db.entity.Word;


@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

   public abstract WordDao wordDao();
   private static WordRoomDatabase INSTANCE;
   private static final String DATABASE_NAME = "word_database";

   public static WordRoomDatabase getDatabase(final Context context) {
       if (INSTANCE == null) {
           synchronized (WordRoomDatabase.class) {
               if (INSTANCE == null) {
                   INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                           WordRoomDatabase.class, DATABASE_NAME)
                           .build();
               }
           }
       }
       return INSTANCE;
   }
}