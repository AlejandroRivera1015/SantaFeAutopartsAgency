package com.autoparts.SantaFeCarsAgency.Exceptions.Order;

public class OrderException extends Exception {

    private Long orderId;
    public  OrderException(String message, Long orderId) {
        super(message);
        this.orderId = orderId;
    }

}
