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
@CrossOrigin(origins = {"https://stepcounter.vercel.app", "http://localhost:3000"})
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable @NotEmpty String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody @NotNull NewUserDTO newUserDTO, HttpServletRequest req) {
        UserModel createdUser = userService.addUser(newUserDTO);
        URI location = URI.create((req.getRequestURI() + "/" + createdUser.getUserId()));
        return ResponseEntity.created(location).body(createdUser);
    }

    @PatchMapping("{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable @NotEmpty String id, @RequestBody @NotNull NewUserDTO updateUserDTO) {
        UserModel updateUser = userService.updateUser(id, updateUserDTO);
        return ResponseEntity.accepted().body(updateUser);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable @NotEmpty String id) {
        userService.deleteUser(id);
    }

}
