package com.momos.orderservice.controller;

import com.momos.orderservice.exception.ApplicationException;
import com.momos.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @MockBean
    OrderService orderService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void when_place_order_given_valid_input() throws Exception {
        String request = "{ " +
                "    \"userId\": 1, " +
                "    \"cartIdsList\": [] " +
                "}";
        mockMvc.perform(post("/order-service/place-order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk());

    }

    @Test
    void when_place_scheduled_order_valid_input() throws Exception {
        String request = "{ " +
                "    \"userId\": null, " +
                "    \"cartIdsList\": [], " +
                "    \"scheduledTime\": \"2022-10-08T12:31:56.100\"\n" +

                "}";
        mockMvc.perform(post("/order-service/place-scheduled-order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest());
    }

    @Test
    void when_place_scheduled_order_valid_input1() throws Exception {
        String request = "{ " +
                "    \"userId\": 1, " +
                "    \"cartIdsList\": [], " +
                "    \"scheduledTime\": \"2022-10-08T12:31:56.100\"\n" +

                "}";
        mockMvc.perform(post("/order-service/place-scheduled-order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk());
    }

    @Test
    void when_place_scheduled_order_invalid_input() throws Exception {
        Mockito.when(orderService.placeScheduledOrder(Mockito.any())).thenThrow(ApplicationException.class);
        String request = "{ " +
                "    \"userId\": 1,\n" +
                "    \"cartIdsList\": [],\n" +
                "    \"scheduledTime\": \"2022-10-08T12:31:56.100\"\n" +

                "}";
        mockMvc.perform(post("/order-service/place-scheduled-order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest());
    }

    @Test
    void when_cancel_order_valid_input() throws Exception {
        mockMvc.perform(post("/order-service/cancel-order/{orderId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void when_get_all_order_valid_input() throws Exception {
        mockMvc.perform(get("/order-service/get-all-order/{userId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void when_get_order_details_valid_input() throws Exception {
        mockMvc.perform(get("/order-service/get-order-details/{orderId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}