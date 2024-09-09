package edu.miu.cqrs.StockCommandService.service;

import edu.miu.cqrs.StockCommandService.integration.JmsSender;
import edu.miu.cqrs.StockCommandService.model.Stock;
import edu.miu.cqrs.StockCommandService.repository.StockRepository;
import edu.miu.cqrs.StockCommandService.service.dto.StockChangeDTO;
import edu.miu.cqrs.StockCommandService.service.dto.StockDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
public class StockService {

    private final StockRepository repository;
    private final JmsSender jmsSender;

    public StockService(StockRepository repository, JmsSender jmsSender) {
        this.repository = repository;
        this.jmsSender = jmsSender;
    }

    @PostMapping
    public StockDTO save(StockDTO stockRequestDTO) {
        Stock stock = StockAdapter.fromStockDTOToStock(stockRequestDTO);
        StockDTO stockDTO = StockAdapter.fromStockToStockDTO(repository.save(stock));

        StockChangeDTO stockChangeDTO = new StockChangeDTO("save", stockDTO);
        jmsSender.sendMessage(stockChangeDTO);

        return stockDTO;
    }

    public StockDTO update(String productNumber, StockDTO stockRequestDTO) {
        Stock stock = StockAdapter.fromStockDTOToStock(stockRequestDTO);
        stock.setProductNumber(productNumber);
        StockDTO stockDTO = StockAdapter.fromStockToStockDTO(repository.save(stock));

        StockChangeDTO stockChangeDTO = new StockChangeDTO("update", stockDTO);
        jmsSender.sendMessage(stockChangeDTO);

        return stockDTO;
    }

    @DeleteMapping
    public void delete(String productNumber) {
        repository.deleteById(productNumber);

        StockChangeDTO stockChangeDTO = new StockChangeDTO("delete", new StockDTO(productNumber, 0));
        jmsSender.sendMessage(stockChangeDTO);
    }

}
