package com.momos.cartservice.service;

import com.momos.cartservice.controller.request.AddToCartRequest;
import com.momos.cartservice.controller.response.CartDetailsResponse;
import com.momos.cartservice.controller.response.Response;
import com.momos.cartservice.exception.ApplicationException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {
    ResponseEntity<Response> addToCart(AddToCartRequest request);

    ResponseEntity<Response> removeFromCart(int cartId) throws ApplicationException;

    ResponseEntity<Response> updateToCart(AddToCartRequest request, int cartId) throws ApplicationException;

    List<CartDetailsResponse> getAllCartDetails(int userId);

    CartDetailsResponse getCartDetails(int cartId) throws ApplicationException;
}
