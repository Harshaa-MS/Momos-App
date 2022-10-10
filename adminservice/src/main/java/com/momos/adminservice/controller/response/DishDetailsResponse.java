package com.momos.adminservice.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DishDetailsResponse {
    private int dishId;

    private String name;

    private Double cost;

    private boolean isAvailable;
}
