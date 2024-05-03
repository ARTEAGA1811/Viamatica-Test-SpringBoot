package com.viamatica.viamatica.rest.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Autowired
//    private ISessionService sessionService;
//
//    @Autowired
//    private JwtUtils jwtUtils;
//
//    @Autowired
//    private IUserService userService;
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<TokenResponse> login(@RequestBody AuthRequest authRequest) {
//
////        if (httpSession.getAttribute("username") != null) {
////            throw new IllegalStateException("There is already a user logged in.");
////        }
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
//        User user = userService.getByUsername(authRequest.getUsername());
//        String token = jwtUtils.generateAccessToken(userDetails.getUsername());
//
////        httpSession.setAttribute("username", userDetails.getUsername());
//
//        Session mySession = new Session();
//        mySession.setLoginDate(LocalDateTime.now());
//        mySession.setUser(user);
//        sessionService.create(mySession);
//
//        return ResponseEntity.ok(TokenResponse.builder()
//                .message("User successfully authenticated.")
//                .token(token)
//                .username(userDetails.getUsername())
//                .build());
//    }
//
////    @PostMapping("/logouts")
////    public ResponseEntity<String> logout(@RequestBody String username) {
//////        if (httpSession.getAttribute("username") == null) {
//////            throw new IllegalStateException("There is no user logged in.");
//////        }
////        User user = userService.getByUsername(username);
//////        httpSession.removeAttribute("username");
////
////        sessionService.create(Session.builder().logoutDate(LocalDateTime.now()).user(user).build());
////
////        return ResponseEntity.status(HttpStatus.OK).body("User successfully logged out.");
////    }
}
