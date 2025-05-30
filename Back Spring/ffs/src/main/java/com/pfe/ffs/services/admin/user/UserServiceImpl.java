package com.pfe.ffs.services.admin.user;

import com.pfe.ffs.dto.ProduitDto;
import com.pfe.ffs.dto.UserDto;
import com.pfe.ffs.entity.Categorie;
import com.pfe.ffs.entity.Produit;
import com.pfe.ffs.entity.User;
import com.pfe.ffs.enums.UserRole;
import com.pfe.ffs.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public List<UserDto> getUsers(){
        List<User> users = userRepo.findAll();
        return users.stream()
                .filter(user -> user.getRole() == UserRole.CLIENT)
                .map(User::getDto)
                .collect(Collectors.toList());
    }

    public Boolean deleteUser(Long id){
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()){
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public UserDto getUserById(Long id){
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()){
            return user.get().getDto();
        }else {
            return null;
        }
    }
}
