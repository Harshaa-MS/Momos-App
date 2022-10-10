package com.momos.orderservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_cart_map_details")
@Data
@NoArgsConstructor
public class OrderCartMapDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "map_id")
    private int mapId;

    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "order_id")
    private int orderId;

    public OrderCartMapDetails(int cartId, int orderId) {
        this.cartId = cartId;
        this.orderId = orderId;
    }
}
