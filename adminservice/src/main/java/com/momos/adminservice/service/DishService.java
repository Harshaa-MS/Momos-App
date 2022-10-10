package com.momos.adminservice.service;

import com.momos.adminservice.controller.request.AddDishRequest;
import com.momos.adminservice.controller.response.DishDetailsResponse;
import com.momos.adminservice.controller.response.Response;
import com.momos.adminservice.exception.ApplicationException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DishService {
    ResponseEntity<Response> addDish(AddDishRequest request);

    DishDetailsResponse getDish(int dishId) throws ApplicationException;

    ResponseEntity<Response> deleteDish(int dishId) throws ApplicationException;

    ResponseEntity<Response> updateDish(AddDishRequest request, int dishId) throws ApplicationException;

    List<DishDetailsResponse> getAllDish();
}
