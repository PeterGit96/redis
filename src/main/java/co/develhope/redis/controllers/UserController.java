package co.develhope.redis.controllers;

import co.develhope.redis.entities.jpa.UserJPA;
import co.develhope.redis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody UserJPA user) {
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @GetMapping
    public List<UserJPA> readAllUsers() {
        return userService.readAllUsers();
    }

    @GetMapping("/{id}")
    public UserJPA readUserById(@PathVariable Long id) {
        return userService.readUserById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUserById(@PathVariable Long id, @RequestBody UserJPA user) {
        userService.updateUserById(id, user);
        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User with id " + id + " deleted successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body("All users deleted successfully");
    }

}
