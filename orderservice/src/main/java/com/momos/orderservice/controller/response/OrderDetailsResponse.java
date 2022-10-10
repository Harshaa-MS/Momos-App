package com.momos.orderservice.controller.response;

import com.momos.orderservice.constants.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class OrderDetailsResponse {
    private int orderId;
    private OrderStatus orderStatus;
    private Date orderTime;
    private List<DishDetails> dishDetailsList;
}
