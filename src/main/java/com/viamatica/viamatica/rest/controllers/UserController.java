package com.viamatica.viamatica.rest.controllers;

import com.viamatica.viamatica.business.port.IPersonService;
import com.viamatica.viamatica.business.port.IRoleService;
import com.viamatica.viamatica.business.port.IUserService;
import com.viamatica.viamatica.domain.dto.Person;
import com.viamatica.viamatica.domain.dto.Role;
import com.viamatica.viamatica.domain.dto.User;
import com.viamatica.viamatica.domain.dto.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    IPersonService personService;
    @Autowired
    private IRoleService roleService;

    @GetMapping("")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        Person person = personService.getById(Long.valueOf(userCreateRequest.getPersonId()));
        List<Role> roles = new ArrayList<>();
        userCreateRequest.getRoles().forEach(roleName -> {
            Role r = roleService.getRoleByName(roleName);
            roles.add(r);
        });

        User user = User.builder()
                .username(userCreateRequest.getUsername())
                .password(userCreateRequest.getPassword())
                .person(person)
                .roles(roles)
                .build();
        User userCreated = userService.create(user);

        return ResponseEntity.ok(userCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok("User deleted");
    }

}
