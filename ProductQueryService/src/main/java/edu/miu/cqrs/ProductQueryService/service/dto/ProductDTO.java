package edu.miu.cqrs.ProductQueryService.service.dto;

public record ProductDTO (
    String productNumber,
    String name,
    double price,
    int numberInStock
) {}