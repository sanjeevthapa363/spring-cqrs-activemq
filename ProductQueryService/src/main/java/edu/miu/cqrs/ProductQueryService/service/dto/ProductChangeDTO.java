package edu.miu.cqrs.ProductQueryService.service.dto;

public record ProductChangeDTO (
        String change,
        ProductDTO productDTO
) {}
