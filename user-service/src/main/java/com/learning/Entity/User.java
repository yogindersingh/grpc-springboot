package com.learning.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class User {

  @Id
  Integer id;
  String name;
  Integer balance;

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getBalance() {
    return balance;
  }
}
