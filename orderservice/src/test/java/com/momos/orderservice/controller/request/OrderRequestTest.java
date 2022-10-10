package com.momos.orderservice.controller.request;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class OrderRequestTest {
    public static OrderRequest buildOrderRequest(){
        return OrderRequest.builder()
                .userId(1)
                .cartIdsList(new ArrayList<>())
                .build();
    }

}