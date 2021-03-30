package com.rafhael.barabas.apipayment.consumers;

import com.rafhael.barabas.apipayment.data.vo.ProductVO;
import com.rafhael.barabas.apipayment.services.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {

    private final ProductService service;

    @Autowired
    public ProductConsumer(ProductService service) {
        this.service = service;
    }

    @RabbitListener(queues = {"${crud.rabbitmq.queue}"})
    public void listenMessages(@Payload ProductVO productVO) {
        var product = service.create(productVO);
    }
}
