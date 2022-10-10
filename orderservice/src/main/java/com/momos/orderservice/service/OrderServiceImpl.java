package com.momos.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.momos.orderservice.constants.OrderStatus;
import com.momos.orderservice.controller.request.OrderRequest;
import com.momos.orderservice.controller.request.ScheduledOrderRequest;
import com.momos.orderservice.controller.response.*;
import com.momos.orderservice.entity.OrderCartMapDetails;
import com.momos.orderservice.entity.OrderDetails;
import com.momos.orderservice.exception.ApplicationException;
import com.momos.orderservice.repository.OrderCartMapRepo;
import com.momos.orderservice.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final OrderCartMapRepo orderCartMapRepo;
    private final RestTemplate restTemplate;

    @Override
    @Transactional
    public ResponseEntity<Response> placeOrder(OrderRequest request) throws ApplicationException {
        LocalTime openTime = LocalTime.of(11, 0);
        LocalTime closeTime = LocalTime.of(20, 59, 59);
        LocalTime orderTime = LocalTime.now();
        if (!orderTime.isBefore(closeTime) && !orderTime.isAfter(openTime)) {
            throw new ApplicationException("Order cannot be accepted");
        }
        OrderDetails orderDetails = new OrderDetails(request.getUserId(), OrderStatus.ACTIVE, null);
        OrderDetails insertedOrderDetails = orderRepo.save(orderDetails);
        request.getCartIdsList().forEach(cartId -> {
            orderCartMapRepo.save(new OrderCartMapDetails(cartId, insertedOrderDetails.getOrderId()));
            restTemplate.delete("http://localhost:8083/cart-service/remove-dish-from-cart/" + cartId);

        });
        return ResponseEntity.ok(new Response(200, "Order placed Successfully", OrderResponse.builder().orderId(insertedOrderDetails.getOrderId()).build()));
    }

    @Override
    public ResponseEntity<Response> cancelOrder(int orderId) throws ApplicationException {
        Optional<OrderDetails> orderDetails = orderRepo.getByOrderIdAndIsDeleted(orderId, false);
        if (orderDetails.isEmpty()) {
            throw new ApplicationException("Order Id not found");
        }
        if (orderDetails.get().getOrderStatus().equals(OrderStatus.CANCELED)) {
            throw new ApplicationException("Order has been already canceled");
        }
        OrderDetails updatedOrderDetails = orderDetails.get();
        updatedOrderDetails.setOrderStatus(OrderStatus.CANCELED);
        orderRepo.save(updatedOrderDetails);
        return ResponseEntity.ok(new Response(200, "Order canceled Successfully", OrderResponse.builder().orderId(updatedOrderDetails.getOrderId()).build()));
    }

    @Override
    public List<OrderResponse> getAllOrder(int userId) {
        return orderRepo.findAllByUserIdAndIsDeleted(userId, false);
    }

    @Override
    public OrderDetailsResponse getOrderDetails(int orderId) throws ApplicationException {
        Optional<OrderDetails> orderDetails = orderRepo.getByOrderIdAndIsDeleted(orderId, false);
        if (orderDetails.isEmpty()) {
            throw new ApplicationException("Order Id not found");
        }
        List<OrderCartMapDetails> orderCartMapDetails = orderCartMapRepo.findAllByOrderId(orderDetails.get().getOrderId());
        List<DishDetails> dishDetailsList = new ArrayList<>();
        orderCartMapDetails.forEach(mapDetails -> {
            ResponseEntity<Response> responseResponseEntity =
                    restTemplate.exchange("http://localhost:8083/cart-service/get-cart-details/" + mapDetails.getCartId(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });
            Response response = responseResponseEntity.getBody();
            System.out.println(response.getData());
            String json = new JSONObject(response.getData()).toString();
            System.out.println(json);
            CartDetailsResponse cartDetailsResponse;
            try {
                cartDetailsResponse = new ObjectMapper().readValue(json, CartDetailsResponse.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            System.out.println(cartDetailsResponse.getDishId());

            ResponseEntity<Response> responseResponseEntity1 =
                    restTemplate.exchange("http://localhost:8082/admin-service/get-dish/" + cartDetailsResponse.getDishId(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });
            Response response1 = responseResponseEntity1.getBody();
            String json1 = new JSONObject(response1.getData()).toString();
            DishDetailsResponse dishDetailsResponse;
            try {
                dishDetailsResponse = new ObjectMapper().readValue(json1, DishDetailsResponse.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            dishDetailsList.add(DishDetails.builder()
                    .dishName(dishDetailsResponse.getName())
                    .dishQuantity(cartDetailsResponse.getDishQuantity())
                    .price(cartDetailsResponse.getPrice())
                    .build());


        });
        return OrderDetailsResponse.builder()
                .orderId(orderDetails.get().getOrderId())
                .orderStatus(orderDetails.get().getOrderStatus())
                .orderTime(orderDetails.get().getCreatedTime())
                .dishDetailsList(dishDetailsList)
                .build();
    }

    @Override
    public ResponseEntity<Response> placeScheduledOrder(ScheduledOrderRequest request) throws ApplicationException {
        LocalTime openTime = LocalTime.of(11, 0);
        LocalTime closeTime = LocalTime.of(20, 59, 59);
        LocalTime orderTime = LocalTime.now();
        if (!orderTime.isBefore(closeTime) && !orderTime.isAfter(openTime)) {
            throw new ApplicationException("Order cannot be accepted");
        }
        OrderDetails orderDetails = new OrderDetails(request.getUserId(), OrderStatus.SCHEDULED, request.getScheduledTime());
        OrderDetails insertedOrderDetails = orderRepo.save(orderDetails);
        request.getCartIdsList().forEach(cartId -> {
            orderCartMapRepo.save(new OrderCartMapDetails(cartId, insertedOrderDetails.getOrderId()));
            restTemplate.delete("http://localhost:8083/cart-service/remove-dish-from-cart/" + cartId);

        });
        return ResponseEntity.ok(new Response(200, "Scheduled Order placed Successfully", OrderResponse.builder().orderId(insertedOrderDetails.getOrderId()).build()));
    }

    @Override
    public void orderScheduler() {
        List<OrderDetails> orderDetailsList = orderRepo.findAllByOrderStatusAndIsDeleted(OrderStatus.SCHEDULED, false);
        orderDetailsList.forEach(orderDetails -> {
            if (orderDetails.getOrderScheduledTime().compareTo(new Date()) == 0 || orderDetails.getOrderScheduledTime().compareTo(new Date()) < 0) {
                orderDetails.setOrderStatus(OrderStatus.ACTIVE);
                orderRepo.save(orderDetails);
            }

        });
    }
}
