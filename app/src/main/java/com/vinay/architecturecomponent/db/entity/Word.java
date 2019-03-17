package com.vinay.architecturecomponent.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

   @PrimaryKey(autoGenerate = true)
   @ColumnInfo(name = "id")
   private int id;

   @ColumnInfo(name = "word")
   private String word;

   @ColumnInfo(name = "description")
   private String desc;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public void setWord(String word) {
      this.word = word;
   }

   public void setDesc(String desc) {
      this.desc = desc;
   }

   public String getDesc() {
      return desc;
   }
//public Word(String word) {this.mWord = word;}

   public String getWord(){
      return word;
   }
}