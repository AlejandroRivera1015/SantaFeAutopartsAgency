package com.autoparts.SantaFeCarsAgency.Exceptions.Product;

public class AvailableProductException extends  ProductException {

    private Long productId;
    public  AvailableProductException(String message, Long productId){
        super(message, productId);
    }
}
