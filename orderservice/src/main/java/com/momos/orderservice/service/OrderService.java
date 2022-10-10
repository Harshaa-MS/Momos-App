package com.momos.orderservice.service;

import com.momos.orderservice.controller.request.OrderRequest;
import com.momos.orderservice.controller.request.ScheduledOrderRequest;
import com.momos.orderservice.controller.response.OrderDetailsResponse;
import com.momos.orderservice.controller.response.OrderResponse;
import com.momos.orderservice.controller.response.Response;
import com.momos.orderservice.exception.ApplicationException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<Response> placeOrder(OrderRequest request) throws ApplicationException;

    ResponseEntity<Response> cancelOrder(int orderId) throws ApplicationException;

    List<OrderResponse> getAllOrder(int userId);

    OrderDetailsResponse getOrderDetails(int orderId) throws ApplicationException;

    ResponseEntity<Response> placeScheduledOrder(ScheduledOrderRequest request) throws ApplicationException;

    void orderScheduler();
}
