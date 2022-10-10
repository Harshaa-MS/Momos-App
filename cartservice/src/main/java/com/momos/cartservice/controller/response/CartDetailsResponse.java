package com.momos.cartservice.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartDetailsResponse {
    private int cartId;
    private int dishId;
    private int dishQuantity;
    private Double price;
}
