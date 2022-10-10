package com.momos.cartservice.controller;

import com.momos.cartservice.controller.request.AddToCartRequest;
import com.momos.cartservice.controller.response.Response;
import com.momos.cartservice.exception.ApplicationException;
import com.momos.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart-service")
public class CartController {
    private final CartService cartService;

    @PostMapping("/add-dish-to-cart")
    public ResponseEntity<Response> addToCart(@RequestBody AddToCartRequest request){
        return cartService.addToCart(request);
    }

    @DeleteMapping("/remove-dish-from-cart/{cartId}")
    public ResponseEntity<Response> deleteFromCart(@PathVariable int cartId) throws ApplicationException {
        return cartService.removeFromCart(cartId);
    }

    @PostMapping("/update-dish-to-cart/{cartId}")
    public ResponseEntity<Response> updateToCart(@RequestBody AddToCartRequest request,@PathVariable int cartId) throws ApplicationException {
        return cartService.updateToCart(request,cartId);
    }

    @GetMapping("/get-all-cart-details/{userId}")
    public ResponseEntity<Response> getAllCartDetails(@PathVariable int userId){
        return ResponseEntity.ok(new Response(200,"Success",cartService.getAllCartDetails(userId)));
    }

    @GetMapping("/get-cart-details/{cartId}")
    public ResponseEntity<Response> getCartDetails(@PathVariable int cartId) throws ApplicationException {
        return ResponseEntity.ok(new Response(200,"Success",cartService.getCartDetails(cartId)));
    }
}
