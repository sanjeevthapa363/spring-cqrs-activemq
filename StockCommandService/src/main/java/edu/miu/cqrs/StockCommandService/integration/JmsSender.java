package edu.miu.cqrs.StockCommandService.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cqrs.StockCommandService.service.dto.StockChangeDTO;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsSender {

    private final JmsTemplate jmsTemplate;

    public JmsSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(StockChangeDTO stockChangeDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String stockChangeDTOAsString = objectMapper.writeValueAsString(stockChangeDTO);
            jmsTemplate.convertAndSend("stockChangeQueue", stockChangeDTOAsString);
            System.out.println("StockCommandService sent stockChangeDTO: " + stockChangeDTO);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }

}
