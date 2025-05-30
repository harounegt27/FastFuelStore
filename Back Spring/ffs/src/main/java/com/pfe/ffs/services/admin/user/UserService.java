package com.pfe.ffs.services.admin.user;

import com.pfe.ffs.dto.UserDto;

import java.util.List;

public interface UserService  {
    List<UserDto> getUsers();
    public Boolean deleteUser(Long id);
    public UserDto getUserById(Long id);

}
