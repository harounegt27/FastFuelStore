package com.pfe.ffs.services.auth;

import com.pfe.ffs.dto.SignupRequest;
import com.pfe.ffs.dto.UserDto;

import java.math.BigDecimal;

public interface AuthSevice {
    UserDto createUser (SignupRequest signupRequest);
    Boolean hasUserWithEmail(String email);

}
