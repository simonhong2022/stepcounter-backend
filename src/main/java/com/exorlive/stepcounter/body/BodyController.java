package com.exorlive.stepcounter.body;

import com.exorlive.stepcounter.model.NewBodyDTO;
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
@RequestMapping("api/bodies")
@CrossOrigin(origins = {"https://stepcounter.vercel.app", "http://localhost:3000"})
public class BodyController {

    private final BodyService bodyService;

    @GetMapping
    public ResponseEntity<List<Body>> getAllBodyInfo() { return ResponseEntity.ok(bodyService.getAllBodyInfo()); }

    @GetMapping("{id}")
    public ResponseEntity<Body> getBody(@PathVariable @NotEmpty String id) {
        return ResponseEntity.ok(bodyService.getSpecificBodyInfo(id));
    }
    @PostMapping
    public ResponseEntity<Body> createBody(@RequestBody @NotNull NewBodyDTO newBodyDTO, HttpServletRequest req) {
        Body createdBody = bodyService.addBody(newBodyDTO);
        URI location = URI.create((req.getRequestURI() + "/" + createdBody.getBodyId()));
        return ResponseEntity.created(location).body(createdBody);
    }

    @DeleteMapping("{id}")
    public void deleteBody(@PathVariable @NotEmpty String id) {
        bodyService.deleteBody(id);
    }

}
