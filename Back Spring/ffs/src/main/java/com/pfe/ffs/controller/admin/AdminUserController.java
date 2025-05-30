package com.pfe.ffs.controller.admin;

import com.pfe.ffs.dto.ProduitDto;
import com.pfe.ffs.dto.StockDto;
import com.pfe.ffs.dto.UserDto;
import com.pfe.ffs.services.admin.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> stockDtos = userService.getUsers();
        return ResponseEntity.ok(stockDtos);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteCat(@PathVariable Long userId){
        boolean delete = userService.deleteUser(userId);
        if (delete){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId){
        UserDto userDto = userService.getUserById(userId);
        if (userDto != null){
            return ResponseEntity.ok(userDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
