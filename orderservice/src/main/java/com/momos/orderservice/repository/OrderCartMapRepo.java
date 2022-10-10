package com.momos.orderservice.repository;

import com.momos.orderservice.entity.OrderCartMapDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderCartMapRepo extends JpaRepository<OrderCartMapDetails,Integer> {
    List<OrderCartMapDetails> findAllByOrderId(int orderId);
}
