package edu.miu.cqrs.StockCommandService.repository;

import edu.miu.cqrs.StockCommandService.model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {
}
