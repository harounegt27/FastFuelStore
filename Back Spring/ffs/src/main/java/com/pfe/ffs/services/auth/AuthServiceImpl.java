package com.pfe.ffs.services.auth;

import com.pfe.ffs.dto.SignupRequest;
import com.pfe.ffs.dto.UserDto;
import com.pfe.ffs.entity.Commande;
import com.pfe.ffs.entity.User;
import com.pfe.ffs.enums.CommandeStatue;
import com.pfe.ffs.enums.UserRole;
import com.pfe.ffs.repository.CommandeRepo;
import com.pfe.ffs.repository.UserRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AuthServiceImpl implements AuthSevice {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CommandeRepo commandeRepo;

    public UserDto createUser (SignupRequest signupRequest){
        User user = new User();

        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRole(UserRole.CLIENT);
        User createdUser = userRepo.save(user);

        Commande commande = new Commande();
        commande.setMontant(BigDecimal.valueOf(0.0));
        commande.setMontant_totale(BigDecimal.valueOf(0.0));
        commande.setCommandeStatue(CommandeStatue.En_attend);
        commande.setUser(createdUser);
        commandeRepo.save(commande);


        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());

        return userDto;
    }

    public Boolean hasUserWithEmail(String email){
        return userRepo.findFirstByEmail(email).isPresent();
    }

    @PostConstruct
    public void createAdminAcc(){
        User adminAcc = userRepo.findByRole(UserRole.ADMIN);
        if (null == adminAcc){
            User user = new User();
            user.setEmail("admin@stir.com");
            user.setName("Admin");
            user.setRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepo.save(user);
        }
    }
}
