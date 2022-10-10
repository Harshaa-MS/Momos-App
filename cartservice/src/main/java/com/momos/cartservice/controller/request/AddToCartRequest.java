package com.momos.cartservice.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddToCartRequest {
    @Min(value = 1,message = "User id cannot be blank")
    private int userId;
    @NotNull(message = "Dish id cannot be blank")
    @Min(value = 1, message = "Dish id cannot be blank")
    private  int dishId;
    @NotNull(message = "Quantity cannot be blank")
    @Min(value = 1,message = "Quantity must be greater than 1")
    private int dishQuantity;
    @NotNull(message = "Price cannot be blank")
    @Min(value = 1,message = "Price must be greater than 1")
    private double price;

}
