package com.example.roomwordsample.room;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

public class WordRepository {
	private WordDao mWordDao;
	private LiveData<List<Word>> mAllWords;

	public WordRepository(Application application) {
		WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
		mWordDao = db.wordDao();
		mAllWords = mWordDao.getAlphabetizedWords();
	}

	public LiveData<List<Word>> getAllWords() {
		return mAllWords;
	}

	public void insert(Word word) {
		WordRoomDatabase.databaseWriteExecutor.execute(() -> {
			mWordDao.insert(word);
		});
	}
}
