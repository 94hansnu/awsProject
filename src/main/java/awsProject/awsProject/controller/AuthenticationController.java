package awsProject.awsProject.controller;


import awsProject.awsProject.database.entity.User;
import awsProject.awsProject.modell.dto.LoginResponse;
import awsProject.awsProject.modell.dto.Registration;
import awsProject.awsProject.service.AuthenticationService;
import awsProject.awsProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody Registration body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }
    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody Registration body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
    @PostMapping("/login-user")
    public LoginResponse login(@RequestBody Registration body) {
        return authenticationService.login(body.getUsername(), body.getPassword());
    }

    @PutMapping("/aukt/{id}")
    @PreAuthorize("hasRole('ADMIN')")//
    public ResponseEntity<User> makeAdmin(@PathVariable Long id) {
        User newAdmin = authenticationService.makeAdmin(id);
        if (newAdmin != null) return ResponseEntity.status(200).body(newAdmin);
        return ResponseEntity.status(400).body(newAdmin);
    }
    @GetMapping("/aukt")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(200).body(users);
    }

}
