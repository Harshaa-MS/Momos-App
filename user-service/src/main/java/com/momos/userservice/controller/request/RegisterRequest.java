package com.momos.userservice.controller.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {

    @NotBlank(message = "Name should not be blank")
    private String name;

    @Min(value = 6000000000L , message = "Invalid mobile number")
    @Max(value = 9999999999L , message = "Invalid mobile number")
    private long mobileNumber;

    @NotBlank(message = "Password should not be blank")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$" , message = "At least 8 char, Contains at least one digit, " +
            "Contains at least one lower alpha char and one upper alpha char, " +
             "Contains at least one char within a set of special chars (@#%$^ etc.), " +
            "Does not contain space, tab, etc.")
    private String password;
}
