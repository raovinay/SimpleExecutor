package com.sample.dao;

import com.sample.models.Email;
import java.util.List;

public interface EmailDAO {
  Email getEmail(int id);
  List<Email> getAllEmails();
  void updateEmail(Email email);

}
