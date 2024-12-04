package com.example.roomwordsample.retrofit;

public class RequestTodo {
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public RequestTodo(String title) {
		this.title = title;
	}

	String title;
}
