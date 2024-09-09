package edu.miu.cqrs.ProductCommandService.service.dto;

public record ProductRequestDTO (
        String name,
        double price
) {}
