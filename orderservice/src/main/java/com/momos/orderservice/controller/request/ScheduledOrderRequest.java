package com.momos.orderservice.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ScheduledOrderRequest {
    @NotNull(message = "UserId cannot be blank")
    @Min(value = 1,message = "UserId cannot be blank")
    private int userId;
    @NotNull(message = "CartIds cannot be blank")
    private List<Integer> cartIdsList;
    @NotNull(message = "Scheduled time cannot be blank")
    private Date scheduledTime;

}
