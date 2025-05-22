package com.autoparts.SantaFeCarsAgency.Service.Order;


import com.autoparts.SantaFeCarsAgency.DTO.Order.OrderDTO;
import com.autoparts.SantaFeCarsAgency.Entity.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface OrderService {
    Order createOrderService(OrderDTO orderRequest);
    Order deleteOrderService(Long orderId);
}
