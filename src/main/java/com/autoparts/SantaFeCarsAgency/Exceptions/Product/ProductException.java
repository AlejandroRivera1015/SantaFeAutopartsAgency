package com.autoparts.SantaFeCarsAgency.Exceptions.Product;

import lombok.Getter;

@Getter
public class ProductException extends Exception {
    private Long productId;

    public  ProductException(String message,Long productId) {
     super(message);
     this.productId = productId;
    }
}
