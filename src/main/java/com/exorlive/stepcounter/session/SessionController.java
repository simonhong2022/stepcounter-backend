package com.exorlive.stepcounter.session;

import com.exorlive.stepcounter.model.NewSessionDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/sessions")
@CrossOrigin(origins = {"https://stepcounter.vercel.app", "http://localhost:3000"})
public class SessionController {

    private final SessionService sessionService;

    @GetMapping
    public ResponseEntity<List<Session>> getAllSessions() {return ResponseEntity.ok(sessionService.getAllSessions());}

    @GetMapping("{id}")
    public ResponseEntity<Session> getSession(@PathVariable @NotEmpty String id) {
        return ResponseEntity.ok(sessionService.getSpecificSession(id));
    }

    @PostMapping
    public ResponseEntity<Session> createSession(@RequestBody @NotNull NewSessionDTO newSessionDTO, HttpServletRequest req) {
        Session createdSession = sessionService.addSession(newSessionDTO);
        URI location = URI.create((req.getRequestURI() + "/" + createdSession.getSessionId()));
        return ResponseEntity.created(location).body(createdSession);
    }

    @DeleteMapping("{id}")
    public void deleteSession(@PathVariable @NotEmpty String id) {sessionService.deleteSession(id);}

}
