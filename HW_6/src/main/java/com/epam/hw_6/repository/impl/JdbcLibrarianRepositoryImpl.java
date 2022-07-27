package com.epam.hw_6.repository.impl;

import com.epam.hw_6.model.entity.User;
import com.epam.hw_6.model.enums.LockStatus;
import com.epam.hw_6.model.enums.Role;
import com.epam.hw_6.repository.JdbcLibrarianRepository;
import com.epam.hw_6.service.exeption.RepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Repository
public class JdbcLibrarianRepositoryImpl implements JdbcLibrarianRepository {
  private static final String INSERT_NEW_USER =
      "insert into user (name, email, password, lock_status, role) values (?,?,?,?,?)";
  private static final String UPDATE_USER =
      "update user set name = ?2, email = ?3, password = ?4 where user_id = ?1";
  private static final String SELECT_ALL_USERS = "select * from user where user.role ='LIBRARIAN'";
  private final JdbcTemplate jdbcTemplate;

  @Override
  public User save(User librarian) throws RepositoryException {
    if (librarian.getId() == 0) {
      jdbcTemplate.update(
          INSERT_NEW_USER,
          librarian.getName(),
          librarian.getPassword(),
          librarian.getEmailAddress(),
          LockStatus.UNLOCKED,
          Role.LIBRARIAN);
    } else {
      jdbcTemplate.update(
          UPDATE_USER,
          librarian.getId(),
          librarian.getName(),
          librarian.getEmailAddress(),
          librarian.getPassword());
    }
      return librarian;
  }

  @Override
  public List<User> getAll() throws RepositoryException {
    return jdbcTemplate.query(SELECT_ALL_USERS, new BeanPropertyRowMapper<User>(User.class));
  }
}
