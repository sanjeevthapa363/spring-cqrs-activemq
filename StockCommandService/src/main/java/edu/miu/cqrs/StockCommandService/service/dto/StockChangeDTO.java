package edu.miu.cqrs.StockCommandService.service.dto;

public record StockChangeDTO (
        String change,
        StockDTO stockDTO
) {}
