package com.momos.userservice.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetailsResponse {
    private int userId;
    private String name;
    private long mobileNumber;
}
