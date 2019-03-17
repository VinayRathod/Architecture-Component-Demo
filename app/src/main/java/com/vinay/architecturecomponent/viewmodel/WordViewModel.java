package com.vinay.architecturecomponent.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;
import com.vinay.architecturecomponent.db.entity.Word;
import com.vinay.architecturecomponent.repo.WordRepository;

import java.util.List;


public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    // private MutableLiveData<List<Word>> elapsedTime = new MutableLiveData<>();

    private LiveData<List<Word>> mAllWords;

    public WordViewModel(Application application) {
        super(application);
        Log.e("ViewModel Call", "WordViewModel");
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }

    public void delete(Word word) {
        mRepository.deleteWord(word);
    }
}