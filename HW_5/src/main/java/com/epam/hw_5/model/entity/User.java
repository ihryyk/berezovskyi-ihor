package com.epam.hw_5.model.entity;

import com.epam.hw_5.model.enums.LockStatus;
import com.epam.hw_5.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  List<Order> orderList = new ArrayList<>();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String emailAddress;

  @Column(name = "password")
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private Role role;

  @JsonManagedReference
  @Enumerated(EnumType.STRING)
  @Column(name = "lock_status")
  private LockStatus lockStatus;
}
