package com.pfe.ffs.services.client.user;

import com.pfe.ffs.dto.UserDto;
import com.pfe.ffs.entity.User;
import com.pfe.ffs.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsernameServiceImpl implements UsernameService{
   private final UserRepo userRepo;

    public UserDto getUserName(Long id)  {
        // Recherche de l'utilisateur par ID dans la base de données
        Optional<User> user = userRepo.findById(id);

        if (user.isPresent()) {
            return user.get().getDto();
        }
        return null;
    }

    public UserDto updateUser(Long userId,UserDto userDto){
        Optional<User> optionalUser = userRepo.findById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            return userRepo.save(user).getDto();
        }else {
            return null;
        }
    }

    public Boolean deleteUser(Long id){
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()){
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
