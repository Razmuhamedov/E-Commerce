package com.example.magazine.controller;

import com.example.magazine.dto.auth.LoginDto;
import com.example.magazine.dto.auth.LoginResult;
import com.example.magazine.dto.profile.ProfileCreateDto;
import com.example.magazine.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid ProfileCreateDto dto){
        String result = authService.signUp(dto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody @Valid LoginDto dto){
        LoginResult result = authService.signIn(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/verification/{token}")
    public ResponseEntity<?> verification(@PathVariable("token") String token){
        String result = authService.verification(token);
        return ResponseEntity.ok(result);
    }

}
