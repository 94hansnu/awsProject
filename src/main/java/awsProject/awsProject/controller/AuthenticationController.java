package awsProject.awsProject.controller;


import awsProject.awsProject.database.entity.User;
import awsProject.awsProject.modell.dto.LoginResponse;
import awsProject.awsProject.modell.dto.Registration;
import awsProject.awsProject.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

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
}
