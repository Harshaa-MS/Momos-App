package com.momos.orderservice.repository;

import com.momos.orderservice.constants.OrderStatus;
import com.momos.orderservice.controller.response.OrderResponse;
import com.momos.orderservice.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<OrderDetails, Integer> {
    Optional<OrderDetails> getByOrderIdAndIsDeleted(int orderId, boolean isDeleted);

    List<OrderResponse> findAllByUserIdAndIsDeleted(int userId, boolean isDeleted);

    List<OrderDetails> findAllByOrderStatusAndIsDeleted(OrderStatus scheduled, boolean isDeleted);
}
