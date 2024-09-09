package edu.miu.cqrs.ProductCommandService.controller;

import edu.miu.cqrs.ProductCommandService.service.ProductService;
import edu.miu.cqrs.ProductCommandService.service.dto.ProductRequestDTO;
import edu.miu.cqrs.ProductCommandService.service.dto.ProductDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ProductDTO save(@RequestBody ProductRequestDTO productRequestDTO) {
        return this.service.save(productRequestDTO);
    }

    @PutMapping("/{productNumber}")
    public ProductDTO update(@PathVariable String productNumber, @RequestBody ProductRequestDTO productRequestDTO) {
        return this.service.update(productNumber, productRequestDTO);
    }

    @DeleteMapping("/{productNumber}")
    public void delete(@PathVariable String productNumber) {
        this.service.delete(productNumber);
    }

}
