package com.sample;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sample.callables.EmailProcessor;
import com.sample.dao.EmailDAO;
import com.sample.dao.EmailDaoImpl;
import com.sample.models.Email;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {

  public static void main(String[] args) throws PropertyVetoException {
    ComboPooledDataSource cpds = new ComboPooledDataSource();
    cpds.setDriverClass("com.mysql.jdbc.Driver");
    cpds.setJdbcUrl( "jdbc:mysql://localhost/email" );
    cpds.setUser("email");
    cpds.setPassword("email");
    EmailDAO emailDao = new EmailDaoImpl(cpds);

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    final List<Email> emails = emailDao.getAllEmails();
    List<Future<Email>> futures = new ArrayList<>();
    for (Email email : emails) {
      futures.add(executorService.submit(new EmailProcessor(email, emailDao)));
    }/// until here, nothing is a blocking operation. no control over what email job will be done
    // first and what sequence will be followed.
    System.out.println("submitted all jobs successfully.");
    for(Future<Email> future: futures) {
      try {
        System.out.println("Job has been processed for: "+ future.get());
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }

    System.out.println("service shutdown");
    executorService.shutdown();
    System.out.println("closing pool");
    cpds.close();
  }
}
