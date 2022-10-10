package com.momos.orderservice.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DishDetails {
    private String dishName;
    private int dishQuantity;
    private Double price;
}
