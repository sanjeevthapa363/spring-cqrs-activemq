package edu.miu.cqrs.StockCommandService.controller;

import edu.miu.cqrs.StockCommandService.service.StockService;
import edu.miu.cqrs.StockCommandService.service.dto.StockDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {

    private final StockService service;

    public StockController(StockService service) {
        this.service = service;
    }

    @PostMapping
    public StockDTO save(@RequestBody StockDTO stockDTO) {
        return service.save(stockDTO);
    }

    @PutMapping("{productNumber}")
    public StockDTO update(@PathVariable String productNumber, @RequestBody StockDTO stockDTO) {
        return service.update(productNumber, stockDTO);
    }

    @DeleteMapping("{productNumber}")
    public void delete(@PathVariable String productNumber) {
        service.delete(productNumber);
    }

}
