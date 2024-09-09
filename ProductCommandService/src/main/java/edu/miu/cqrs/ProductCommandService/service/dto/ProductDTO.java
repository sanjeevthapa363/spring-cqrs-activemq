package edu.miu.cqrs.ProductCommandService.service.dto;

public record ProductDTO(
        String productNumber,
        String name,
        double price
) {}
