package com.learning.service.handler;

import com.learning.Entity.PortfolioItem;
import com.learning.Entity.User;
import com.learning.common.Ticker;
import com.learning.exceptions.InsufficientBalanceException;
import com.learning.exceptions.InsufficientQuantityException;
import com.learning.exceptions.InvalidTickerException;
import com.learning.exceptions.UnknownUserException;
import com.learning.repository.PortFolioRepository;
import com.learning.repository.UserRepository;
import com.learning.user.StockTradeRequest;
import com.learning.user.StockTradeResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockHandler {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PortFolioRepository portFolioRepository;

  @Transactional
  public StockTradeResponse buyStock(StockTradeRequest stockTradeRequest) {
    //validation
    validateTicker(stockTradeRequest.getTicker());
    User user = userRepository.findById(stockTradeRequest.getUserId())
        .orElseThrow(() -> new UnknownUserException(stockTradeRequest.getUserId()));
    int totalPrice = stockTradeRequest.getPrice() * stockTradeRequest.getQuantity();
    validateUserBalance(user.getId(), user.getBalance(), totalPrice);


    //Update Actions
    user.setBalance(user.getBalance() - totalPrice);
    portFolioRepository.findByUserIdAndTicker(user.getId(), stockTradeRequest.getTicker()).ifPresentOrElse(
        portfolioItem -> portfolioItem.setQuantity(portfolioItem.getQuantity() + stockTradeRequest.getQuantity()),
        () -> portFolioRepository.save(createPortfolio(stockTradeRequest, user))
    );

    return createStockTradeResponse(stockTradeRequest, user);
  }

  @Transactional
  public StockTradeResponse sellStock(StockTradeRequest stockTradeRequest) {
    //validation
    validateTicker(stockTradeRequest.getTicker());
    User user = userRepository.findById(stockTradeRequest.getUserId())
        .orElseThrow(() -> new UnknownUserException(stockTradeRequest.getUserId()));

    portFolioRepository.findByUserIdAndTicker(user.getId(), stockTradeRequest.getTicker()).filter(val->val.getQuantity()>=stockTradeRequest.getQuantity())
        .orElseThrow(()->new InsufficientQuantityException(user.getId()));

    int totalPrice = stockTradeRequest.getPrice() * stockTradeRequest.getQuantity();

    //Update Actions
    user.setBalance(user.getBalance() + totalPrice);
    portFolioRepository.findByUserIdAndTicker(user.getId(), stockTradeRequest.getTicker()).ifPresentOrElse(
        portfolioItem -> portfolioItem.setQuantity(portfolioItem.getQuantity() - stockTradeRequest.getQuantity()),
        () -> portFolioRepository.save(createPortfolio(stockTradeRequest, user))
    );

    return createStockTradeResponse(stockTradeRequest, user);
  }

  private static StockTradeResponse createStockTradeResponse(StockTradeRequest stockTradeRequest, User user) {
    return StockTradeResponse.newBuilder().setBalance(user.getBalance()).setUserId(user.getId())
        .setAction(stockTradeRequest.getAction()).setActionValue(stockTradeRequest.getActionValue())
        .setQuantity(stockTradeRequest.getQuantity()).setTicker(stockTradeRequest.getTicker())
        .setPrice(stockTradeRequest.getPrice())
        .setTotalPrice(stockTradeRequest.getPrice() * stockTradeRequest.getQuantity())
        .build();
  }

  private static PortfolioItem createPortfolio(StockTradeRequest stockTradeRequest, User user) {
    PortfolioItem portfolioItem = new PortfolioItem();
    portfolioItem.setUserId(user.getId());
    portfolioItem.setTicker(stockTradeRequest.getTicker());
    portfolioItem.setQuantity(stockTradeRequest.getQuantity());
    return portfolioItem;
  }


  private void validateUserBalance(Integer id, Integer balance, int totalPrice) {
    if (totalPrice > balance) {
      throw new InsufficientBalanceException(id);
    }
  }

  private void validateTicker(Ticker ticker) {
    if (Ticker.UNKNOWN.equals(ticker)) {
      throw new InvalidTickerException();
    }
  }


}
