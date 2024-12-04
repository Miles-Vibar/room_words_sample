package com.example.roomwordsample.retrofit;

public class Todo {
   String userId;
   String id;
   String title;

   public String getCompleted() {
      return completed;
   }

   public void setCompleted(String completed) {
      this.completed = completed;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   String completed;
}
