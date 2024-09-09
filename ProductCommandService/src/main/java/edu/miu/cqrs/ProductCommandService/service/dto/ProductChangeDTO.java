package edu.miu.cqrs.ProductCommandService.service.dto;

public record ProductChangeDTO (
        String change,
        ProductDTO productDTO
) {}
