package com.pfe.ffs.controller;

import com.pfe.ffs.dto.AuthentificationRequest;
import com.pfe.ffs.dto.SignupRequest;
import com.pfe.ffs.dto.UserDto;
import com.pfe.ffs.entity.User;
import com.pfe.ffs.repository.UserRepo;
import com.pfe.ffs.services.auth.AuthSevice;
import com.pfe.ffs.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final UserRepo userRepo;

    private final JwtUtil jwtUtil;
    private static final String TOKEN_PREFIX = "EGTBNJ";
    private static final String HEADER_STRING = "Authorization";

    private final AuthSevice authSevice;


    @PostMapping("/authentification")
    public void CreateAuthToken(@RequestBody AuthentificationRequest authentificationRequest,
                                HttpServletResponse response) throws IOException, JSONException {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentificationRequest.getUsername(),
                    authentificationRequest.getPassword()));
        } catch (BadCredentialsException e){
            throw new BadCredentialsException("Nom d'utilisateur ou mot de pass incorrecte !");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authentificationRequest.getUsername());
        Optional<User> optionalUser = userRepo.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        if (optionalUser.isPresent()){
            response.getWriter().write(new JSONObject()
                    .put("userId",optionalUser.get().getId())
                    .put("role",optionalUser.get().getRole())
                    .toString()
            );
            response.addHeader("Access-Control-Expose-Headers","Authorization");
            response.setHeader("Access-Control-Allow-Methods","POST, GET, PUT, OPTIONS, DELETE");
            response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, "+
                    "X-Requested-With, Content-Type, Accept, X-Custom-header");

            response.addHeader(HEADER_STRING , TOKEN_PREFIX + jwt );
        }
    }
    @PostMapping("/sign-up")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
        if (authSevice.hasUserWithEmail(signupRequest.getEmail())){
            return new ResponseEntity<>("Utilisateur déjà existe !", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto userDto = authSevice.createUser(signupRequest);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }



}
