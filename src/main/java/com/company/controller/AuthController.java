package com.company.controller;

import com.company.dto.AuthDTO;
import com.company.dto.RegistrationDTO;
import com.company.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Api(tags = "Auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    private Logger log = LoggerFactory.getLogger(AuthController.class);

    @ApiOperation(value = "login", notes = "Mathod used for login and getting taken", nickname = "nicname")
    @PostMapping("/login")
    public ResponseEntity<?> create(@RequestBody @Valid AuthDTO dto) {
        log.info("Authorization: {}", dto);
        return ResponseEntity.ok(authService.login(dto));
    }

    @ApiOperation(value = "registration", notes = "Mathod used for registration", nickname = "nicname")
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody @Valid RegistrationDTO dto) {
        log.info("Registration: {}", dto);
        authService.registration(dto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "verification", notes = "Mathod used Email verification", nickname = "nicname")
    @GetMapping("/verification/{jwt}")
    public ResponseEntity<?> verification(@PathVariable("jwt") String jwt) {
        authService.verification(jwt);
        return ResponseEntity.ok().build();
    }

}
