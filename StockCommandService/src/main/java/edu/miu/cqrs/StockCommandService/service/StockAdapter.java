package edu.miu.cqrs.StockCommandService.service;

import edu.miu.cqrs.StockCommandService.model.Stock;
import edu.miu.cqrs.StockCommandService.service.dto.StockDTO;

public class StockAdapter {

    public static StockDTO fromStockToStockDTO(Stock stock) {
        return new StockDTO(stock.getProductNumber(), stock.getQuantity());
    }

    public static Stock fromStockDTOToStock(StockDTO stockDTO) {
        return new Stock(stockDTO.productNumber(), stockDTO.quantity());
    }

}
