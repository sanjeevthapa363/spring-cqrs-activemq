package edu.miu.cqrs.ProductCommandService.service;

import edu.miu.cqrs.ProductCommandService.domain.Product;
import edu.miu.cqrs.ProductCommandService.service.dto.ProductRequestDTO;
import edu.miu.cqrs.ProductCommandService.service.dto.ProductDTO;

public class ProductAdapter {

    public static Product fromProductRequestDTOToProduct(ProductRequestDTO productRequestDTO) {
        return new Product(null, productRequestDTO.name(), productRequestDTO.price());
    }

    public static ProductDTO fromProductToProductDTO(Product product) {
        return new ProductDTO(product.getProductNumber(), product.getName(), product.getPrice());
    }

}
