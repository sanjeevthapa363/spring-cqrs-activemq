package edu.miu.cqrs.ProductQueryService.service.dto;

public record StockDTO(
        String productNumber,
        int quantity
) {}
