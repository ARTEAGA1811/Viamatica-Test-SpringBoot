package com.viamatica.viamatica.rest.controllers;

import com.viamatica.viamatica.business.port.ISessionService;
import com.viamatica.viamatica.domain.dto.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/sessions")
public class SessionController {
    @Autowired
    private ISessionService sessionService;

    @GetMapping("")
    public ResponseEntity<List<Session>> getSessions() {
        List<Session> sessions = sessionService.getAll();
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable Long id) {
        Session session = sessionService.getById(id);
        return ResponseEntity.ok(session);
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<Session>> getSessionsByUserId(@PathVariable Long userId) {
//        List<Session> sessions = sessionService.getByUserId(userId);
//        return ResponseEntity.ok(sessions);
//    }

    @PostMapping("")
    public ResponseEntity<Session> createSession(@RequestBody Session session) {
        Session newSession = sessionService.create(session);
        return ResponseEntity.ok(newSession);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> updateSession(@PathVariable Long id, @RequestBody Session session) {
        Session updatedSession = sessionService.update(id, session);
        return ResponseEntity.ok(updatedSession);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSession(@PathVariable Long id) {
        sessionService.delete(id);
        return ResponseEntity.ok("Session deleted");
    }

    @GetMapping("/login-dates/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<String>> getLoginDatesByUserId(@PathVariable Long userId) {
        List<String> loginDatesString = sessionService.getLoginDatesByUserId(userId)
                .stream()
                .filter(Objects::nonNull)
                .map(String::valueOf)
                .collect(Collectors.toList());

        return ResponseEntity.ok(loginDatesString);
    }

    @GetMapping("/logout-dates/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<String>> getLogoutDatesByUserId(@PathVariable Long userId) {
        List<String> logoutDatesString = sessionService.getLogoutDatesByUserId(userId)
                .stream()
                .filter(Objects::nonNull)
                .map(String::valueOf)
                .collect(Collectors.toList());

        return ResponseEntity.ok(logoutDatesString);
    }
}
