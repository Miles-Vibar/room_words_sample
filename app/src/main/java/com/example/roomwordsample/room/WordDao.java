package com.example.roomwordsample.room;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
interface WordDao {
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	void insert(Word word);

	@Query("DELETE FROM word_table")
	void deleteAll();

	@Query("SELECT * FROM word_table ORDER BY word ASC")
	LiveData<List<Word>> getAlphabetizedWords();
}
