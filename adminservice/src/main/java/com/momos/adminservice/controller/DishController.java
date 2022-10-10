package com.momos.adminservice.controller;

import com.momos.adminservice.controller.request.AddDishRequest;
import com.momos.adminservice.controller.response.Response;
import com.momos.adminservice.exception.ApplicationException;
import com.momos.adminservice.service.DishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-service")
public class DishController {

    private final DishService dishService;

    @PostMapping("/add-dish")
    public ResponseEntity<Response> addDish(@Valid @RequestBody AddDishRequest request){
       return dishService.addDish(request);
    }

    @GetMapping("/get-dish/{dishId}")
    public ResponseEntity<Response> getDish(@PathVariable int dishId) throws ApplicationException {
        return ResponseEntity.ok(new Response(200,"Success",dishService.getDish(dishId)));

    }

    @DeleteMapping("/delete-dish/{dishId}")
    public ResponseEntity<Response>  deleteDish(@PathVariable int dishId) throws ApplicationException {
        return dishService.deleteDish(dishId);
    }

    @PostMapping("/update-dish/{dishId}")
    public ResponseEntity<Response> updateDish(@Valid @RequestBody AddDishRequest request, @PathVariable int dishId) throws ApplicationException {
        return dishService.updateDish(request,dishId);

    }

    @GetMapping("/get-all-dish")
    public ResponseEntity<Response> getAllDish() {
        return ResponseEntity.ok(new Response(200,"Success",dishService.getAllDish()));

    }


}
