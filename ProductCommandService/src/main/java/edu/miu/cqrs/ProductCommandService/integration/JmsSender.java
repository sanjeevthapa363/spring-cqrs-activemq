package edu.miu.cqrs.ProductCommandService.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cqrs.ProductCommandService.service.dto.ProductChangeDTO;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsSender {

    private final JmsTemplate jmsTemplate;

    public JmsSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(ProductChangeDTO productChangeDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String productChangeEventDTOAsString = objectMapper.writeValueAsString(productChangeDTO);
            jmsTemplate.convertAndSend("productChangeQueue", productChangeEventDTOAsString);
            System.out.println("ProductCommandService sent ProductChangeDTO: " + productChangeEventDTOAsString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}
