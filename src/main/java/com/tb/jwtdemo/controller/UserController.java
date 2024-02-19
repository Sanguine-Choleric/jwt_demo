package com.tb.jwtdemo.controller;

import com.tb.jwtdemo.entity.AuthRequest;
import com.tb.jwtdemo.entity.UserInfo;
import com.tb.jwtdemo.service.JwtService;
import com.tb.jwtdemo.service.UserInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserInfoService service;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public UserController(UserInfoService service, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @GetMapping("/home")
    public String home() {
        return "Reached home";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    @GetMapping("/user/userPage")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userPage() {
        return "Reached User Page";
    }

    @GetMapping("/admin/adminPage")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminPage() {
        return "Reached Admin Page";
    }

    @PostMapping("/getToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request!");
        }
    }
}
