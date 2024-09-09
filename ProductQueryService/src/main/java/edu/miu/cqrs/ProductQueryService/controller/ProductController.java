package edu.miu.cqrs.ProductQueryService.controller;

import edu.miu.cqrs.ProductQueryService.service.ProductService;
import edu.miu.cqrs.ProductQueryService.service.dto.ProductDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("{productNumber}")
    public ProductDTO get(@PathVariable String productNumber) {
        return service.get(productNumber);
    }

}
