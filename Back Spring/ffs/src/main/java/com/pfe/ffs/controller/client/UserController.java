package com.pfe.ffs.controller.client;

import com.pfe.ffs.dto.ProduitDto;
import com.pfe.ffs.dto.UserDto;
import com.pfe.ffs.entity.User;
import com.pfe.ffs.services.client.user.UsernameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/client")
@RequiredArgsConstructor
public class UserController {
    private final UsernameService usernameService;

    @GetMapping("/username/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        UserDto userDto = usernameService.getUserName(userId);
        if (userDto != null){
            return ResponseEntity.ok(userDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/userupdate/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @ModelAttribute UserDto userDto) throws IOException {
        UserDto dto = usernameService.updateUser(userId,userDto);
        if (dto != null){
            return ResponseEntity.ok(dto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteCat(@PathVariable Long userId){
        boolean delete = usernameService.deleteUser(userId);
        if (delete){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }


}
