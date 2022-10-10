package com.momos.cartservice.controller;

import com.momos.cartservice.exception.ApplicationException;
import com.momos.cartservice.service.CartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CartController.class)
class CartControllerTest {

    @MockBean
    CartService cartService;

    @InjectMocks
    CartController cartController;

    @Autowired
    private MockMvc mockMvc;



    @Test
    void addToCart() throws Exception {
        String request = "{\n" +
                "    \"userId\": 1,\n" +
                "    \"dishId\": 1,\n" +
                "    \"dishQuantity\": 1,\n" +
                "    \"price\": 1\n" +
                "}";
        mockMvc.perform(post("/cart-service/add-dish-to-cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk());

    }

    @Test
    void addToCart1() throws Exception {
        String request = "{\n" +
                "    \"userId\": null,\n" +
                "    \"dishId\": null,\n" +
                "    \"dishQuantity\": 1,\n" +
                "    \"price\": 1\n" +
                "}";
        mockMvc.perform(post("/cart-service/add-dish-to-cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest());

    }

//    @Test
//    void addToCart2() throws Exception {
//        Mockito.when(cartService.addToCart(Mockito.any())).thenThrow(ApplicationException.class);
//        String request = "{\n" +
//                "    \"userId\": null,\n" +
//                "    \"dishId\": 1,\n" +
//                "    \"dishQuantity\": 1,\n" +
//                "    \"price\": 1\n" +
//                "}";
//        mockMvc.perform(post("/cart-service/add-dish-to-cart")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(request))
//                .andExpect(status().isBadRequest());
//
//    }

    @Test
    void deleteFromCart() throws Exception {

        mockMvc.perform(delete("/cart-service/remove-dish-from-cart/{cardId}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


    @Test
    void updateToCart() throws Exception {
        String request = "{\n" +
                "    \"userId\": 1,\n" +
                "    \"dishId\": 1,\n" +
                "    \"dishQuantity\": 1,\n" +
                "    \"price\": 1\n" +
                "}";
        mockMvc.perform(post("/cart-service/update-dish-to-cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk());

    }


    @Test
    void getAllCartDetails() throws Exception {
        mockMvc.perform(get("/cart-service/get-all-cart-details/{userId}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCartDetails() throws Exception {
        mockMvc.perform(get("/cart-service/get-cart-details/{cartId}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}