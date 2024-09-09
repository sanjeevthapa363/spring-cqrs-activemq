package edu.miu.cqrs.StockCommandService.service.dto;

public record StockDTO (
        String productNumber,
        int quantity
) {}
