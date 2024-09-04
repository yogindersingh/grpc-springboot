package com.learning.controller;


import com.learning.service.StockService;
import com.learning.user.StockTradeRequest;
import com.learning.user.StockTradeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockTradeController {

  @Autowired
  private StockService stockService;

  @PostMapping(value = "/trade",produces = MediaType.APPLICATION_JSON_VALUE)
  public StockTradeResponse stockTradeResponse(
      @RequestBody
      StockTradeRequest stockTradeRequest) {
    return stockService.getStockTrade(stockTradeRequest);
  }


}
