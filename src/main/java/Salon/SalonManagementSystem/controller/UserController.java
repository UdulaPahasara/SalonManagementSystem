package Salon.SalonManagementSystem.controller;

import Salon.SalonManagementSystem.model.Users;
import Salon.SalonManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")  // Allow React
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Users loginData) {

        Users loggedIn = userService.authenticate(
                loginData.getUsername(),
                loginData.getPassword(),
                loginData.getRole().getRoleName()
        );

        if (loggedIn != null) {
            loggedIn.setPassword(null); // Hide password
            return ResponseEntity.ok(loggedIn);
        }

        return ResponseEntity.status(401).body("Invalid username, password, or role");
    }
}
