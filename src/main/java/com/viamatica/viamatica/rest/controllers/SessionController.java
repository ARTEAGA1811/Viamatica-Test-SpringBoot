package com.viamatica.viamatica.rest.controllers;

import com.viamatica.viamatica.business.port.ISessionService;
import com.viamatica.viamatica.domain.dto.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
