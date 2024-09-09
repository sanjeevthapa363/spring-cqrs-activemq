package edu.miu.cqrs.ProductQueryService.repository;

import edu.miu.cqrs.ProductQueryService.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
