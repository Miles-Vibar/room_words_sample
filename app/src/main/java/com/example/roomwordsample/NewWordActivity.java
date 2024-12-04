package com.example.roomwordsample;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomwordsample.retrofit.RequestTodo;
import com.example.roomwordsample.retrofit.Todo;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewWordActivity extends AppCompatActivity {

	public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

	private EditText mEditWordView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EdgeToEdge.enable(this);
		setContentView(R.layout.activity_new_word);

		mEditWordView = findViewById(R.id.edit_word);
		final Button button = findViewById(R.id.button_save);

		Retrofit r = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com").addConverterFactory(GsonConverterFactory.create()).build();

		MainActivity.RequestTodos todos = r.create(MainActivity.RequestTodos.class);

		button.setOnClickListener(view -> {
			todos.postTodo(new RequestTodo(mEditWordView.getText().toString())).enqueue(new Callback<Todo>() {
				@Override
				public void onResponse(Call<Todo> call, Response<Todo> response) {
					System.out.println(response.body().getId() + ": " + response.body().getTitle());
				}

				@Override
				public void onFailure(Call<Todo> call, Throwable throwable) {
					System.out.println(throwable.getMessage());
				}
			});

			Intent replyIntent = new Intent();
			if (TextUtils.isEmpty(mEditWordView.getText())) {
				setResult(RESULT_CANCELED, replyIntent);
			} else {
				String word = mEditWordView.getText().toString();
				replyIntent.putExtra(EXTRA_REPLY, word);
				setResult(RESULT_OK, replyIntent);
			}
			finish();
		});

		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});
	}
}