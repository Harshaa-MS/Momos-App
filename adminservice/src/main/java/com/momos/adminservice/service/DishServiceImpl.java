package com.momos.adminservice.service;

import com.momos.adminservice.controller.request.AddDishRequest;
import com.momos.adminservice.controller.response.DishDetailsResponse;
import com.momos.adminservice.controller.response.Response;
import com.momos.adminservice.entity.DishDetails;
import com.momos.adminservice.exception.ApplicationException;
import com.momos.adminservice.repository.DishRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepo dishRepo;

    @Override
    public ResponseEntity<Response> addDish(AddDishRequest request) {
        dishRepo.save(new DishDetails(request.getName(), request.getCost(), request.isAvailable()));
        return ResponseEntity.ok(new Response(200, "Dish Details Saved Successfully", null));
    }

    @Override
    public DishDetailsResponse getDish(int dishId) throws ApplicationException {
        Optional<DishDetails> dishDetails = dishRepo.findByDishIdAndIsDeleted(dishId, false);
        if (dishDetails.isPresent()) {
            return DishDetailsResponse.builder()
                    .dishId(dishDetails.get().getDishId())
                    .name(dishDetails.get().getName())
                    .cost(dishDetails.get().getCost())
                    .isAvailable(dishDetails.get().isAvailable())
                    .build();
        } else {
            throw new ApplicationException("Dish Id not found");
        }
    }

    @Override
    public ResponseEntity<Response> deleteDish(int dishId) throws ApplicationException {
        Optional<DishDetails> dishDetails = dishRepo.findByDishIdAndIsDeleted(dishId, false);
        if (dishDetails.isPresent()) {
            DishDetails updatedDishDetails = dishDetails.get();
            updatedDishDetails.setDeleted(true);
            dishRepo.save(updatedDishDetails);

            return ResponseEntity.ok(new Response(200, "Dish Deleted Successfully", null));
        } else {
            throw new ApplicationException("Dish Id not found");
        }
    }

    @Override
    public ResponseEntity<Response> updateDish(AddDishRequest request, int dishId) throws ApplicationException {
        Optional<DishDetails> dishDetails = dishRepo.findByDishIdAndIsDeleted(dishId, false);
        if (dishDetails.isPresent()) {
            DishDetails updatedDishDetails = dishDetails.get();
            updatedDishDetails.setName(request.getName());
            updatedDishDetails.setCost(request.getCost());
            updatedDishDetails.setAvailable(request.isAvailable());
            dishRepo.save(updatedDishDetails);

            return ResponseEntity.ok(new Response(200, "Dish data Updated Successfully", null));
        } else {
            throw new ApplicationException("Dish Id not found");
        }    }

    @Override
    public List<DishDetailsResponse> getAllDish() {
        return dishRepo.findAllByIsDeleted(false);
    }
}

