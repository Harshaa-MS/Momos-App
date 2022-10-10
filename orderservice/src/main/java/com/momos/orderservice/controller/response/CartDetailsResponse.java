package com.momos.orderservice.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailsResponse {
    private int cartId;
    private int dishId;
    private int dishQuantity;
    private Double price;
}
