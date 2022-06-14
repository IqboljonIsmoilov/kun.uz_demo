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


    @ApiOperation(value = "create", notes = "Mathod used for create", nickname = "nickname")
    @PostMapping("/adm")
    public ResponseEntity<?> created(@RequestBody @Valid ProfileDTO dto,
                                     HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("created Profile: {}{}", dto, ProfileController.class);
        return ResponseEntity.ok(profileService.created(dto));
    }


    @ApiOperation(value = "get", notes = "Mathod used for get", nickname = "nickname")
    @GetMapping("/public")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(profileService.getAll());
    }


    @ApiOperation(value = "get", notes = "Mathod used for get", nickname = "nickname")
    @GetMapping("/adm")
    public ResponseEntity<?> get(@RequestBody Integer page,
                                 @RequestBody Integer size,
                                 HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.getAll(page, size));
    }


    @ApiOperation(value = "getById", notes = "Mathod used for getById", nickname = "nickname")
    @GetMapping("/public/id")
    public ResponseEntity<?> getById(HttpServletRequest request) {
        Integer id = JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(profileService.getById(id));
    }


    @ApiOperation(value = "update", notes = "Mathod used for update", nickname = "nickname")
    @PutMapping("/adm/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid ProfileDTO dto,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("update Profile: {}{}", dto, ProfileController.class);
        return ResponseEntity.ok(profileService.update(dto, id));
    }


    @ApiOperation(value = "uploadImage", notes = "Mathod used for uploadImage", nickname = "nickname")
    @PutMapping("/public/image/{id}")
    public ResponseEntity<?> uploadImage(@PathVariable("id") String id,
                                         HttpServletRequest request) {
        Integer pid = JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(profileService.uploadImage(pid, id));
    }


    @ApiOperation(value = "delete", notes = "Mathod used for delete", nickname = "nickname")
    @DeleteMapping("/adm/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.delete(id));
    }
}