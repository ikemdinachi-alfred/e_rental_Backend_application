package com.alfredTech.e_rental_application.controllers;
import com.alfredTech.e_rental_application.data.models.User;
import com.alfredTech.e_rental_application.dtos.reponse.Response;
import com.alfredTech.e_rental_application.dtos.requests.LoginRequest;
import com.alfredTech.e_rental_application.services.serviceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;



    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody User user) {
       Response response = userService.registerUser(user);
       return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest) {
        Response response = userService.loginUser(loginRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
