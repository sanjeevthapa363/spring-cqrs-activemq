package edu.miu.cqrs.ProductQueryService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cqrs.ProductQueryService.service.dto.StockChangeDTO;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class StockChangeListener {

    private final ProductService productService;

    public StockChangeListener(ProductService productService) {
        this.productService = productService;
    }

    @JmsListener(destination = "stockChangeQueue")
    public void receiveMessage(final String stockChangeDTOAsString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            StockChangeDTO stockChangeDTO = objectMapper.readValue(stockChangeDTOAsString, StockChangeDTO.class);
            System.out.println("StockChangeListener received StockChangeDTO: " + stockChangeDTO);
            productService.handle(stockChangeDTO);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }

}
