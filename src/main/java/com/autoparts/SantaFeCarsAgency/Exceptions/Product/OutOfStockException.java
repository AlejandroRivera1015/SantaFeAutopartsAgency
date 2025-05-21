package com.autoparts.SantaFeCarsAgency.Exceptions.Product;

public class OutOfStockException extends ProductException {

    public  OutOfStockException(String message, Long pruductId) {
        super(message,pruductId);
    }
}
