package com.company.controller;

import com.company.dto.AuthDTO;
import com.company.dto.RegistrationDTO;
import com.company.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
@Api(tags = "Auth")
public class AuthController {

    private final AuthService authService;


    @ApiOperation(value = "login", notes = "Mathod used for login and getting taken")
    @PostMapping("/login")
    public ResponseEntity<?> create(@RequestBody @Valid AuthDTO requestDTO) {
        log.info("Authorization: {}{}", requestDTO, AuthController.class);
        return ResponseEntity.ok(authService.login(requestDTO));
    }


    @ApiOperation(value = "registration", notes = "Mathod used for registration")
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody @Valid RegistrationDTO requestDTO) {
        log.info("Registration: {}{}", requestDTO, AuthController.class);
        authService.registration(requestDTO);
        return ResponseEntity.ok().build();
    }


    @ApiOperation(value = "verification", notes = "Mathod used Email verification")
    @GetMapping("/verification/{jwt}")
    public ResponseEntity<?> verification(@PathVariable("jwt") String jwt) {
        authService.verification(jwt);
        return ResponseEntity.ok().build();
    }
}