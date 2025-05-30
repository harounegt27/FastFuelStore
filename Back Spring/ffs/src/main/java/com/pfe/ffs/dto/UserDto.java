package com.pfe.ffs.dto;

import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String email;
    private String name;
    private String userRole;
}
