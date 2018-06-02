package com.sample.callables;

import com.sample.dao.EmailDAO;
import com.sample.models.Email;
import java.util.concurrent.Callable;

public class EmailProcessor implements Callable<Email> {

  private final Email email;
  private final EmailDAO emailDAO;

  public EmailProcessor(Email email, EmailDAO emailDAO) {
    this.email = email;
    this.emailDAO = emailDAO;
  }
  @Override
  public Email call() throws Exception {
    System.out.println("PROCESSING FOR EMAIL: "+ email);
    //send email
    //update processed
    email.setProcessed(true);
    emailDAO.updateEmail(email);
    return email;
  }
}
