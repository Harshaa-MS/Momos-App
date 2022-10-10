package com.momos.cartservice.repository;

import com.momos.cartservice.entity.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<CartDetails, Integer> {
    Optional<CartDetails> findByCartIdAndIsDeleted(int cartId, boolean isDeleted);

    List<CartDetails> findAllByUserIdAndIsDeleted(int userId, boolean isDeleted);

    Optional<CartDetails> findByCartId(int cartId);
}
