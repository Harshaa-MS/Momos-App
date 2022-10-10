package com.momos.orderservice.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderRequest {
    @NotNull(message = "UserId cannot be blank")
    private int userId;
    @NotNull(message = "CartIds cannot be blank")
    private List<Integer> cartIdsList;
}
