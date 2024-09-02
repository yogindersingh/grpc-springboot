package com.learning.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import com.learning.common.Ticker;
import jakarta.persistence.Table;

@Entity
@Table(name = "portfolio_item")
public class PortfolioItem {

  @Id
  @GeneratedValue()
  Integer id;

  @Column(name = "customer_id")
  Integer userId;

  Ticker ticker;

  Integer quantity;

  public Integer getId() {
    return id;
  }

  public Integer getUserId() {
    return userId;
  }

  public Ticker getTicker() {
    return ticker;
  }

  public Integer getQuantity() {
    return quantity;
  }
}
