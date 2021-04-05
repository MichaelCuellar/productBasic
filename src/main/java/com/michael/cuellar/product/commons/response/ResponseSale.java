package com.michael.cuellar.product.commons.response;

import lombok.Data;

@Data
public class ResponseSale {

    private double subTotal;
    private int costDelivery;
    private double total;
    private Long idSale;
}
