package com.pfe.ffs.services.client.user;

import com.pfe.ffs.dto.UserDto;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;

public interface UsernameService {
    public UserDto getUserName(Long id) ;
    public UserDto updateUser(Long userId,UserDto userDto);
    public Boolean deleteUser(Long id);
}
