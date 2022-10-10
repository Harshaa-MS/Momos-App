package com.momos.userservice.repository;

import com.momos.userservice.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer> {
    Optional<UserDetails> findByUserIdAndIsDeleted(int userId, boolean isDeleted);

    Optional<UserDetails> findByLoginIdAndIsDeleted(long mobileNumber, boolean b);
}
