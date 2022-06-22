package com.company.controller;

import com.company.dto.ProfileDTO;
import com.company.enums.ProfileRole;
import com.company.service.ProfileService;
import com.company.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {


    private final ProfileService profileService;


    @ApiOperation(value = "create", notes = "Mathod used for create")
    @PostMapping("/adm")
    public ResponseEntity<?> created(@RequestBody @Valid ProfileDTO requestDTO,
                                     HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("created Profile: {}{}", requestDTO, ProfileController.class);
        return ResponseEntity.ok(profileService.created(requestDTO));
    }


    @ApiOperation(value = "get", notes = "Mathod used for get")
    @GetMapping("/public")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(profileService.getAll());
    }


    @ApiOperation(value = "get", notes = "Mathod used for get")
    @GetMapping("/adm")
    public ResponseEntity<?> get(@RequestBody Integer page,
                                 @RequestBody Integer size,
                                 HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.getAll(page, size));
    }


    @ApiOperation(value = "get By Id", notes = "Mathod used for get By Id")
    @GetMapping("/public/id")
    public ResponseEntity<?> getById(HttpServletRequest request) {
        Integer id = JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(profileService.getById(id));
    }


    @ApiOperation(value = "update", notes = "Mathod used for update")
    @PutMapping("/adm/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid ProfileDTO updateDTO,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("update Profile: {}{}", updateDTO, ProfileController.class);
        return ResponseEntity.ok(profileService.update(updateDTO, id));
    }


    @ApiOperation(value = "upload Image", notes = "Mathod used for upload Image")
    @PutMapping("/public/{id}")
    public ResponseEntity<?> uploadImage(@PathVariable("id") String id,
                                         HttpServletRequest request) {
        Integer pid = JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(profileService.uploadImage(pid, id));
    }


    @ApiOperation(value = "delete", notes = "Mathod used for delete")
    @DeleteMapping("/adm/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.delete(id));
    }
}