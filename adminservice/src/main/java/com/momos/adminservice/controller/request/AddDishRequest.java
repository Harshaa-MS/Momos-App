package com.momos.adminservice.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddDishRequest {
    @NotBlank(message = "Dish name cannot be empty")
    private String name;

    @Min(value = 1 ,message ="Cost cannot be empty" )
    private Double cost;

    @NotNull(message = "Available status cannot be empty")
    private boolean isAvailable;
}
