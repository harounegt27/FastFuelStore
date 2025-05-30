package com.pfe.ffs.entity;

import com.pfe.ffs.dto.UserDto;
import com.pfe.ffs.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private UserRole role;

    @Lob
    @Column(name = "img")
    private byte[] img;

    public UserDto getDto(){
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setEmail(email);
        userDto.setName(name);
        userDto.setUserRole(role.toString());

        return userDto;
    }
}
