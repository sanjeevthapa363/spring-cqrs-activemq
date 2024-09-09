package edu.miu.cqrs.ProductQueryService.service;

import edu.miu.cqrs.ProductQueryService.model.Product;
import edu.miu.cqrs.ProductQueryService.repository.ProductRepository;
import edu.miu.cqrs.ProductQueryService.service.dto.ProductChangeDTO;
import edu.miu.cqrs.ProductQueryService.service.dto.ProductDTO;
import edu.miu.cqrs.ProductQueryService.service.dto.StockChangeDTO;
import edu.miu.cqrs.ProductQueryService.service.dto.StockDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductDTO get(String productNumber) {
        Optional<Product> product = repository.findById(productNumber);
        if (product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ProductAdapter.getProductDTOFromProduct(product.get());
    }


    public ProductDTO save(ProductDTO productDTO) {
        Product product = ProductAdapter.getProductFromProductDTO(productDTO);
        return ProductAdapter.getProductDTOFromProduct(repository.save(product));
    }

    public ProductDTO update(String productNumber, ProductDTO productDTO) {
        Product product = ProductAdapter.getProductFromProductDTO(productDTO);
        product.setProductNumber(productNumber);
        return ProductAdapter.getProductDTOFromProduct(repository.save(product));
    }

    public void delete(String productNumber) {
        repository.deleteById(productNumber);
    }

    public void handle(ProductChangeDTO productChangeDTO) {
        String change = productChangeDTO.change();
        ProductDTO productDTO = productChangeDTO.productDTO();

        switch (change) {
            // if (change.equals("save"))
            case "save" -> {
                save(productDTO);
                System.out.println("Product Saved: " + productDTO);
            }
            // else if (change.equals("update"))
            case "update" -> {
                update(productDTO.productNumber(), productDTO);
                System.out.println("Product Updated: " + productDTO);
            }
            // else if (change.equals("delete"))
            case "delete" -> {
                delete(productDTO.productNumber());
                System.out.println("Product Deleted: " + productDTO.productNumber());
            }
        }
    }

    public void handle(StockChangeDTO stockChangeDTO) {
        StockDTO stockDTO = stockChangeDTO.stockDTO();
        Optional<Product> optionalProduct = repository.findById(stockDTO.productNumber());

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setNumberInStock(stockDTO.quantity());
            repository.save(product);
            System.out.println("Stock " + stockChangeDTO.change() + " : " + product);
        }
    }


}
