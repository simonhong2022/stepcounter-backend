package com.exorlive.stepcounter.user;

import com.exorlive.stepcounter.model.NewUserDTO;
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
@RequestMapping("api/users")
@CrossOrigin(origins = {"https://activitycounter.vercel.app/", "http://localhost:3000"})
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{email}")
    public ResponseEntity<UserModel> getUserByEmail(@PathVariable @NotEmpty String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody @NotNull NewUserDTO newUserDTO, HttpServletRequest req) {
        UserModel createdUser = userService.addUser(newUserDTO);
        URI location = URI.create((req.getRequestURI() + "/" + createdUser.getUserId()));
        return ResponseEntity.created(location).body(createdUser);
    }

    @PatchMapping("{email}")
    public ResponseEntity<UserModel> updateUser(@PathVariable @NotEmpty String email, @RequestBody @NotNull NewUserDTO updateUserDTO) {
        UserModel updateUser = userService.updateUser(email, updateUserDTO);
        return ResponseEntity.accepted().body(updateUser);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable @NotEmpty String id) {
        userService.deleteUser(id);
    }

}
