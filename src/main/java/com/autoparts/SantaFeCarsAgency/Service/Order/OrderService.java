package com.autoparts.SantaFeCarsAgency.Service.Order;


import com.autoparts.SantaFeCarsAgency.DTO.Order.OrderDTO;
import com.autoparts.SantaFeCarsAgency.Entity.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Order createOrderService(OrderDTO orderRequest);
}
