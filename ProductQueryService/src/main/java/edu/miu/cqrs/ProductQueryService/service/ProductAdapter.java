package edu.miu.cqrs.ProductQueryService.service;

import edu.miu.cqrs.ProductQueryService.model.Product;
import edu.miu.cqrs.ProductQueryService.service.dto.ProductDTO;

import java.util.Optional;

public class ProductAdapter {

    public static Product getProductFromProductDTO(ProductDTO productDTO) {
        return new Product(productDTO.productNumber(), productDTO.name(), productDTO.price(), productDTO.numberInStock());
    }

    public static ProductDTO getProductDTOFromProduct(Product product) {
        return new ProductDTO(product.getProductNumber(), product.getName(), product.getPrice(), product.getNumberInStock());
    }

}
