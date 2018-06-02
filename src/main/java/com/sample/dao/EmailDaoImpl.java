package com.sample.dao;

import com.sample.models.Email;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class EmailDaoImpl implements EmailDAO {

  DataSource dataSource;

  public EmailDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public Email getEmail(final int id) {
    try (Connection conn = dataSource.getConnection();
         PreparedStatement statement = conn.prepareStatement("select * from email where id = ?");
    ) {
      statement.setInt(1, id);
      try (ResultSet resultSet = statement.executeQuery()) {
        Email email = new Email();
        email.setEmail(resultSet.getString("email"));
        email.setProcessed(resultSet.getBoolean("processed"));
        email.setId(resultSet.getInt("id"));
        return email;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Email> getAllEmails() {
    try (Connection conn = dataSource.getConnection();
         PreparedStatement statement = conn.prepareStatement("select * from email");
         ResultSet resultSet = statement.executeQuery()
    ) {
      List<Email> emails = new ArrayList<>();
      while (resultSet.next()) {
        Email email = new Email();
        email.setEmail(resultSet.getString("email"));
        email.setProcessed(resultSet.getBoolean("processed"));
        email.setId(resultSet.getInt("id"));
        emails.add(email);
      }
      return emails;
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
    return null;
  }

  @Override
  public void updateEmail(final Email email) {
    try (Connection conn = dataSource.getConnection();
         PreparedStatement statement = conn.prepareStatement("update email set processed = ? "
                                                             + "where id = ?")
    ) {
      statement.setBoolean(1, email.isProcessed());
      statement.setInt(2, email.getId());
      statement.executeUpdate();
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
  }
}
