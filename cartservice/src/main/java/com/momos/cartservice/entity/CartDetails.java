package com.momos.cartservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "cart_details")
@Data
@NoArgsConstructor
public class CartDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "dish_id")
    private int dishId;

    @Column(name = "dish_quantity")
    private int dishQuantity;

    @Column(name = "price")
    private Double price;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @CreationTimestamp
    @Column(name = "created_time")
    private Date createdTime;

    @UpdateTimestamp
    @Column(name = "updated_time")
    private Date updatedTime;

    public CartDetails(int userId, int dishId, int dishQuantity, Double price) {
        this.userId = userId;
        this.dishId = dishId;
        this.dishQuantity = dishQuantity;
        this.price = price;
    }
}
