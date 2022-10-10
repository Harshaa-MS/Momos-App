package com.momos.orderservice.scheduler;

import com.momos.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderScheduler {
    private final OrderService orderService;

    @Scheduled(cron = "*/10 * * * * *" )
    public void orderScheduler(){
        orderService.orderScheduler();
    }
}
