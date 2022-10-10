package com.momos.adminservice.repository;

import com.momos.adminservice.controller.response.DishDetailsResponse;
import com.momos.adminservice.entity.DishDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepo extends JpaRepository<DishDetails,Integer> {
    Optional<DishDetails> findByDishIdAndIsDeleted(int dishId, boolean isDeleted);
    List<DishDetailsResponse> findAllByIsDeleted(boolean isDeleted);
}
