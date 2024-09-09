package edu.miu.cqrs.ProductCommandService.service;

import edu.miu.cqrs.ProductCommandService.domain.Product;
import edu.miu.cqrs.ProductCommandService.integration.JmsSender;
import edu.miu.cqrs.ProductCommandService.repository.ProductRepository;
import edu.miu.cqrs.ProductCommandService.service.dto.ProductChangeDTO;
import edu.miu.cqrs.ProductCommandService.service.dto.ProductRequestDTO;
import edu.miu.cqrs.ProductCommandService.service.dto.ProductDTO;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final JmsSender jmsSender;

    public ProductService(ProductRepository repository, JmsSender jmsSender) {
        this.repository = repository;
        this.jmsSender = jmsSender;
    }

    public ProductDTO save(ProductRequestDTO productRequestDTO) {
        Product product = ProductAdapter.fromProductRequestDTOToProduct(productRequestDTO);
        ProductDTO productDTO =  ProductAdapter.fromProductToProductDTO(this.repository.save(product));

        ProductChangeDTO productChangeDTO = new ProductChangeDTO("save", productDTO);
        jmsSender.sendMessage(productChangeDTO);

        return productDTO;
    }

    public ProductDTO update(String productNumber, ProductRequestDTO productRequestDTO) {
        Product product = ProductAdapter.fromProductRequestDTOToProduct(productRequestDTO);
        product.setProductNumber(productNumber);
        ProductDTO productDTO = ProductAdapter.fromProductToProductDTO(this.repository.save(product));

        ProductChangeDTO productChangeDTO = new ProductChangeDTO("update", productDTO);
        jmsSender.sendMessage(productChangeDTO);

        return productDTO;
    }

    public void delete(String productNumber) {
        repository.deleteById(productNumber);

        ProductChangeDTO productChangeDTO = new ProductChangeDTO("delete", new ProductDTO(productNumber, null, 0.0));
        jmsSender.sendMessage(productChangeDTO);
    }



}
