package com.momos.userservice.controller;

import com.momos.userservice.controller.request.LoginRequest;
import com.momos.userservice.controller.request.RegisterRequest;
import com.momos.userservice.controller.response.Response;
import com.momos.userservice.exception.ApplicationException;
import com.momos.userservice.service.UserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser (@Valid @RequestBody RegisterRequest request){
        return  userDetailsService.registerUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login (@Valid @RequestBody LoginRequest request) throws ApplicationException {
        return  userDetailsService.login(request);
    }

    @GetMapping("/user-details/{userId}")
    public ResponseEntity<Response> getUserDetails(@PathVariable int userId) throws ApplicationException {
         return ResponseEntity.ok(new Response(200,"Success",userDetailsService.getUserDetails(userId)));
    }

    @DeleteMapping("/user-details/{userId}")
    public ResponseEntity<Response> deleteUserDetails(@PathVariable int userId) throws ApplicationException {
        return userDetailsService.deleteUserDetails(userId);
    }

    @PostMapping("/update-details/{userId}")
    public ResponseEntity<Response> updateUserDetails (@Valid @RequestBody RegisterRequest request,@PathVariable int userId) throws ApplicationException {
        return  userDetailsService.updateUserDetails(request,userId);
    }
}
