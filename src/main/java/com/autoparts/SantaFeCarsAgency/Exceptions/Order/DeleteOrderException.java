package com.autoparts.SantaFeCarsAgency.Exceptions.Order;

public class DeleteOrderException extends OrderException {

    public DeleteOrderException(Long orderId) {
        super("error deleting order", orderId);
    }
}
