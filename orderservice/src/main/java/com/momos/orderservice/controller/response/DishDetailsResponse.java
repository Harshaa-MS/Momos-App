package com.momos.orderservice.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DishDetailsResponse {
    private int dishId;

    private String name;

    private Double cost;

    private boolean isAvailable;
}
