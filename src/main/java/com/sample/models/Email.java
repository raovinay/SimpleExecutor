package com.sample.models;

public class Email {
  private int id;
  private String email;
  private boolean processed;

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public boolean isProcessed() {
    return processed;
  }

  public void setProcessed(final boolean processed) {
    this.processed = processed;
  }

  @Override
  public String toString() {
    return "Email{" +
           "id=" + id +
           ", email='" + email + '\'' +
           ", processed=" + processed +
           '}';
  }
}
