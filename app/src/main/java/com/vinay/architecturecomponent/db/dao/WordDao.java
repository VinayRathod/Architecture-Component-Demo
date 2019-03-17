package com.vinay.architecturecomponent.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.vinay.architecturecomponent.db.entity.Word;

import java.util.List;

@Dao
public interface WordDao {

   @Insert
   void insert(Word word);

   @Query("DELETE FROM word_table")
   void deleteAll();

   @Delete
   void delete(Word word);

   @Query("SELECT * from word_table ORDER BY word ASC")
   LiveData<List<Word>> getAllWords();

   @Query("SELECT * from word_table WHERE word = :name")
   List<Word> isAvailableList(String name);

   @Update
   void update(Word word);
}