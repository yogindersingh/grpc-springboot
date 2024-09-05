package com.learning.repository;

import com.learning.Entity.PortfolioItem;
import com.learning.common.Ticker;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PortFolioRepository extends CrudRepository<PortfolioItem,Integer> {

  List<PortfolioItem> findAllByUserId(Integer userId);

  Optional<PortfolioItem> findByUserIdAndTicker(Integer userId, Ticker ticker);
}
