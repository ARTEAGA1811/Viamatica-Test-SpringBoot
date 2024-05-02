package com.viamatica.viamatica.rest.controllers;

import com.viamatica.viamatica.business.port.IRoleService;
import com.viamatica.viamatica.domain.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/roles")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @GetMapping("")
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> roles = roleService.getAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Role role = roleService.getById(id);
        return ResponseEntity.ok(role);
    }

    @PostMapping("")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role newRole = roleService.create(role);
        return ResponseEntity.ok(newRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {
        Role updatedRole = roleService.update(id, role);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.ok("Role deleted");
    }

}
