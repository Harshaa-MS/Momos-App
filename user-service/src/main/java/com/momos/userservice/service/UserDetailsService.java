package com.momos.userservice.service;

import com.momos.userservice.controller.request.LoginRequest;
import com.momos.userservice.controller.request.RegisterRequest;
import com.momos.userservice.controller.response.Response;
import com.momos.userservice.controller.response.UserDetailsResponse;
import com.momos.userservice.exception.ApplicationException;
import org.springframework.http.ResponseEntity;

public interface UserDetailsService {
     ResponseEntity<Response> registerUser(RegisterRequest request);
     UserDetailsResponse getUserDetails(int userId) throws ApplicationException;

    ResponseEntity<Response> deleteUserDetails(int userId) throws ApplicationException;

    ResponseEntity<Response> login(LoginRequest request) throws ApplicationException;

    ResponseEntity<Response> updateUserDetails(RegisterRequest request,int userId) throws ApplicationException;
}
