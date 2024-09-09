package edu.miu.cqrs.ProductQueryService.service.dto;

public record StockChangeDTO (
        String change,
        StockDTO stockDTO
) {}
