package com.momos.orderservice.controller;

import com.momos.orderservice.controller.request.OrderRequest;
import com.momos.orderservice.controller.request.ScheduledOrderRequest;
import com.momos.orderservice.controller.response.Response;
import com.momos.orderservice.exception.ApplicationException;
import com.momos.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place-order")
    public ResponseEntity<Response> placeOrder(@Valid @RequestBody OrderRequest request) throws ApplicationException {
       return orderService.placeOrder(request);
    }

    @PostMapping("/place-scheduled-order")
    public ResponseEntity<Response> placeScheduledOrder(@Valid @RequestBody ScheduledOrderRequest request) throws ApplicationException {
        return orderService.placeScheduledOrder(request);
    }

    @PostMapping("/cancel-order/{orderId}")
    public ResponseEntity<Response> cancelOrder(@PathVariable int orderId) throws ApplicationException {
        return orderService.cancelOrder(orderId);
    }

    @GetMapping("/get-all-order/{userId}")
    public ResponseEntity<Response> getAllOrder(@PathVariable int userId){
        return ResponseEntity.ok(new Response(200,"Success",orderService.getAllOrder(userId)));
    }

    @GetMapping("/get-order-details/{orderId}")
    public ResponseEntity<Response> getOrderDetails(@PathVariable int orderId) throws ApplicationException {
        return ResponseEntity.ok(new Response(200,"Success",orderService.getOrderDetails(orderId)));
    }

}
