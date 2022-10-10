package com.momos.cartservice.service;

import com.momos.cartservice.controller.request.AddToCartRequest;
import com.momos.cartservice.controller.response.CartDetailsResponse;
import com.momos.cartservice.controller.response.Response;
import com.momos.cartservice.entity.CartDetails;
import com.momos.cartservice.exception.ApplicationException;
import com.momos.cartservice.repository.CartRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepo cartRepo;
    @Override
    public ResponseEntity<Response> addToCart(AddToCartRequest request) {
        cartRepo.save(new CartDetails(request.getUserId(), request.getDishId(), request.getDishQuantity(), request.getPrice()));
        return ResponseEntity.ok(new Response(200, "Dish added to Cart Successfully", null));
    }

    @Override
    public ResponseEntity<Response> removeFromCart(int cartId) throws ApplicationException {
        Optional<CartDetails> cartDetails = cartRepo.findByCartIdAndIsDeleted(cartId, false);
        if (cartDetails.isPresent()) {
            CartDetails updatedDishDetails = cartDetails.get();
            updatedDishDetails.setDeleted(true);
            cartRepo.save(updatedDishDetails);

            return ResponseEntity.ok(new Response(200, "Dish removed from Cart Successfully", null));
        } else {
            throw new ApplicationException("cart Id not found");
        }    }

    @Override
    public ResponseEntity<Response> updateToCart(AddToCartRequest request, int cartId) throws ApplicationException {
        Optional<CartDetails> cartDetails = cartRepo.findByCartIdAndIsDeleted(cartId, false);
        if (cartDetails.isPresent()) {
            CartDetails updatedDishDetails = cartDetails.get();
            updatedDishDetails.setDishId(request.getDishId());
            updatedDishDetails.setDishQuantity(request.getDishQuantity());
            updatedDishDetails.setPrice(request.getPrice());
            cartRepo.save(updatedDishDetails);

            return ResponseEntity.ok(new Response(200, "Cart details updated  Successfully", null));
        } else {
            throw new ApplicationException("cart Id not found");
        }       }

    @Override
    public List<CartDetailsResponse> getAllCartDetails(int userId) {
        List<CartDetails> cartDetailsList = cartRepo.findAllByUserIdAndIsDeleted(userId, false);
        List<CartDetailsResponse> cartDetailsResponseList = new ArrayList<>();
        cartDetailsList.forEach(cartDetails -> {
            cartDetailsResponseList.add(CartDetailsResponse.builder()
                            .cartId(cartDetails.getCartId())
                            .dishId(cartDetails.getDishId())
                            .dishQuantity(cartDetails.getDishQuantity())
                            .price(cartDetails.getPrice())
                    .build());
        });
        return cartDetailsResponseList;
    }

    @Override
    public CartDetailsResponse getCartDetails(int cartId) throws ApplicationException {
        Optional<CartDetails> cartDetails = cartRepo.findByCartId(cartId);
        if (cartDetails.isEmpty()){
            throw new ApplicationException("Cart Id not found");
        }
        return CartDetailsResponse.builder()
                .cartId(cartDetails.get().getCartId())
                .dishId(cartDetails.get().getDishId())
                .dishQuantity(cartDetails.get().getDishQuantity())
                .price(cartDetails.get().getPrice())
                .build();
    }
}
