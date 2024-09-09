package edu.miu.cqrs.ProductQueryService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cqrs.ProductQueryService.service.dto.ProductChangeDTO;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ProductChangeListener {

    private final ProductService productService;

    public ProductChangeListener(ProductService productService) {
        this.productService = productService;
    }

    @JmsListener(destination = "productChangeQueue")
    public void receiveMessage(final String productChangeDTOAsString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProductChangeDTO productChangeDTO = objectMapper.readValue(productChangeDTOAsString, ProductChangeDTO.class);
            System.out.println("ProductChangeListener received ProductChangeDTO: " + productChangeDTO);
            productService.handle(productChangeDTO);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }

}
