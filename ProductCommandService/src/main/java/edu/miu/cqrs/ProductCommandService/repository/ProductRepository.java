package edu.miu.cqrs.ProductCommandService.repository;

import edu.miu.cqrs.ProductCommandService.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
