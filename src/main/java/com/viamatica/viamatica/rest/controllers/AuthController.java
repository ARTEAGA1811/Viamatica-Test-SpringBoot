package com.viamatica.viamatica.rest.controllers;


import com.viamatica.viamatica.business.port.ISessionService;
import com.viamatica.viamatica.business.port.IUserService;
import com.viamatica.viamatica.business.service.UserDetailsServiceImpl;
import com.viamatica.viamatica.domain.dto.Session;
import com.viamatica.viamatica.domain.dto.User;
import com.viamatica.viamatica.domain.dto.request.AuthRequest;
import com.viamatica.viamatica.domain.dto.response.TokenResponse;
import com.viamatica.viamatica.domain.repository.IUserRepository;
import com.viamatica.viamatica.errors.UserBlockedException;
import com.viamatica.viamatica.security.jwt.JwtUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private ISessionService sessionService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> login(@RequestBody AuthRequest authRequest, HttpSession httpSession) {

        if (httpSession.getAttribute("username") != null) {
            throw new IllegalStateException("There is already a user logged in.");
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            User user = userService.getByUsername(userDetails.getUsername());
            if (user.getStatus().equals("blocked")) {
                throw new UserBlockedException("User is blocked. Please contact the administrator.");
            }
            String token = jwtUtils.generateAccessToken(userDetails.getUsername());
            Session mySession = new Session();
            mySession.setLoginDate(LocalDateTime.now());
            mySession.setUser(user);
            sessionService.create(mySession);

            return ResponseEntity.ok(TokenResponse.builder()
                    .message("User successfully authenticated.")
                    .token(token)
                    .username(userDetails.getUsername())
                    .build());
        } catch (BadCredentialsException e) {
            Optional<User> user = userRepository.getUserByUsername(authRequest.getUsername());
            if (user.isPresent()) {
                user.get().setFailedAttempts(user.get().getFailedAttempts() + 1);
                if (user.get().getFailedAttempts() > 3) {
                    user.get().setStatus("blocked");
                }
                userRepository.update(user.get());
            }
            throw e;
        }

//        httpSession.setAttribute("username", userDetails.getUsername());


    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody HashMap<String, String> body) {


//        if (httpSession.getAttribute("username") == null) {
//            throw new IllegalStateException("There is no user logged in.");
//        }
        User user = userService.getByUsername(body.get("username"));
//        httpSession.removeAttribute("username");

        sessionService.create(Session.builder().logoutDate(LocalDateTime.now()).user(user).build());

        return ResponseEntity.status(HttpStatus.OK).body("User successfully logged out.");
    }
}
