package com.vinay.architecturecomponent.repo;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import com.vinay.architecturecomponent.db.WordRoomDatabase;
import com.vinay.architecturecomponent.db.dao.WordDao;
import com.vinay.architecturecomponent.db.entity.Word;

import java.util.List;

public class WordRepository {

   private WordDao mWordDao;
   private LiveData<List<Word>> mAllWords;

   public WordRepository(Context application) {
       WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
       mWordDao = db.wordDao();
       mAllWords = mWordDao.getAllWords();
   }

   public LiveData<List<Word>> getAllWords() {
       return mAllWords;
   }

   public void insert (final Word word) {
       new Thread(new Runnable() {
           public void run() {
               List<Word> mList = mWordDao.isAvailableList(word.getWord());
               if(mList.size() > 0){
                   word.setId(mList.get(0).getId());
                   mWordDao.update(word);
               }else
                   mWordDao.insert(word);
           }
       }).start();
   }

    public void deleteWord(Word word) {
        new DeleteAsyncTask(mWordDao).execute(word);
    }

    /**
     * Delete data in databse in thread
     */
    protected class DeleteAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        DeleteAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}