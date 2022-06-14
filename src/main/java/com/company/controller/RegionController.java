package com.company.controller;

import com.company.dto.RegionDTO;
import com.company.enums.LangEnum;
import com.company.enums.ProfileRole;
import com.company.service.RegionService;
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
@RequestMapping("/region")
public class RegionController {

    private final RegionService regionService;


    @ApiOperation(value = "create", notes = "Mathod used for create")
    @PostMapping("/adm")
    public ResponseEntity<?> created(@RequestBody @Valid RegionDTO requestDTO,
                                     HttpServletRequest request) {
        Integer id = JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("created Region: {}{}", requestDTO, RegionController.class);
        return ResponseEntity.ok(regionService.created(requestDTO, id));
    }


    @ApiOperation(value = "get", notes = "Mathod used for get")
    @GetMapping("/public")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(regionService.getAll());
    }


    @ApiOperation(value = "get", notes = "Mathod used for get")
    @GetMapping("/adm/{lang}")
    public ResponseEntity<?> get(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "5") Integer size,
                                 HttpServletRequest request,
                                 @PathVariable("lang") LangEnum lang) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(regionService.getAll(page, size, lang));
    }


    @ApiOperation(value = "get By Id", notes = "Mathod used for get By Id")
    @GetMapping("/public")
    public ResponseEntity<?> getById(HttpServletRequest request) {
        Integer id = JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(regionService.getById(id));
    }


    @ApiOperation(value = "update", notes = "Mathod used for update")
    @PutMapping("/adm/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid RegionDTO updateDTO,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("update Region: {}{}", updateDTO, RegionController.class);
        return ResponseEntity.ok(regionService.update(updateDTO, id));
    }


    @ApiOperation(value = "delete", notes = "Mathod used for delete")
    @PutMapping("/adm/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(regionService.delete(id));
    }
}