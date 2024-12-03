package com.example.roomwordsample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.roomwordsample.room.Word;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
	private  WordViewModel mWordViewModel;

	public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EdgeToEdge.enable(this);
		setContentView(R.layout.activity_main);

		RecyclerView recyclerView = findViewById(R.id.recyclerview);
		final WordListAdapter adapter = new WordListAdapter(new WordListAdapter.WordDiff());
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

		mWordViewModel.getAllWords().observe(this, adapter::submitList);

		FloatingActionButton fab = findViewById(R.id.fab);

		fab.setOnClickListener(view -> {
			Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
			startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
		});

		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
			Word word = new Word(Objects.requireNonNull(data.getStringExtra(NewWordActivity.EXTRA_REPLY)));
			mWordViewModel.insert(word);
		} else {
			Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
		}
	}
}