package com.momos.orderservice.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
    private int orderId;
}
