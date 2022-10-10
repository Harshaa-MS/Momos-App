package com.momos.userservice.service;

import com.momos.userservice.controller.request.LoginRequest;
import com.momos.userservice.controller.request.RegisterRequest;
import com.momos.userservice.controller.response.Response;
import com.momos.userservice.controller.response.UserDetailsResponse;
import com.momos.userservice.entity.UserDetails;
import com.momos.userservice.exception.ApplicationException;
import com.momos.userservice.repository.UserDetailsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

    private final UserDetailsRepo userDetailsRepo;
    @Override
    public ResponseEntity<Response> registerUser(RegisterRequest request) {
        userDetailsRepo.save(new UserDetails(request.getName(),request.getMobileNumber(),request.getPassword()));
        return ResponseEntity.ok(new Response(200,"User Details Saved Successfully",null));

    }

    @Override
    public UserDetailsResponse getUserDetails(int userId) throws ApplicationException {
        Optional<UserDetails> userDetails = userDetailsRepo.findByUserIdAndIsDeleted(userId,false);
        if(userDetails.isPresent()){
            return new UserDetailsResponse(userDetails.get().getUserId(),userDetails.get().getName(),userDetails.get().getLoginId());
        }else{
            throw new ApplicationException("User Id not found");
        }
    }

    @Override
    public ResponseEntity<Response> deleteUserDetails(int userId) throws ApplicationException{
        Optional<UserDetails> userDetails = userDetailsRepo.findByUserIdAndIsDeleted(userId, false);
        if(userDetails.isPresent()){
            UserDetails updatedUserDetails = userDetails.get();
            updatedUserDetails.setDeleted(true);
            userDetailsRepo.save(updatedUserDetails);

            return ResponseEntity.ok(new Response(200,"User Deleted Successfully",null));
        }else{
            throw new ApplicationException("User Id not found");
        }
    }

    @Override
    public ResponseEntity<Response> login(LoginRequest request) throws ApplicationException {
        Optional<UserDetails> userDetails = userDetailsRepo.findByLoginIdAndIsDeleted(request.getMobileNumber(), false);
        if(userDetails.isPresent()){
            if(userDetails.get().getPassword().equals(request.getPassword())){
                return ResponseEntity.ok(new Response(200,"Logged in Successfully",null));
            }else{
                throw new ApplicationException("Invalid credential");
            }

        }else{
            throw new ApplicationException("User Id not found");
        }    }

    @Override
    public ResponseEntity<Response> updateUserDetails(RegisterRequest request, int userId) throws ApplicationException {
        Optional<UserDetails> userDetails = userDetailsRepo.findByUserIdAndIsDeleted(userId, false);
        if(userDetails.isPresent()){
            UserDetails updatedUserDetails = userDetails.get();
            updatedUserDetails.setName(request.getName());
            updatedUserDetails.setPassword(request.getPassword());
            updatedUserDetails.setLoginId(request.getMobileNumber());
            userDetailsRepo.save(updatedUserDetails);

            return ResponseEntity.ok(new Response(200,"User Details Updated Successfully",null));
        }else{
            throw new ApplicationException("User Id not found");
        }    }
}

