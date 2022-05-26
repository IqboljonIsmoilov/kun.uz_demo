package com.company.controller;

import com.company.dto.RegionDTO;
import com.company.enums.LangEnum;
import com.company.enums.ProfileRole;
import com.company.service.RegionService;
import com.company.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService regionService;
    private Logger log = LoggerFactory.getLogger(RegionController.class);


    @ApiOperation(value = "create", notes = "Mathod used for create", nickname = "nickname")
    @PostMapping("/adm")
    public ResponseEntity<?> created(@RequestBody @Valid RegionDTO dto,
                                     HttpServletRequest request) {
        Integer id = JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("created Region: {}", dto);
        return ResponseEntity.ok(regionService.created(dto, id));
    }


    @ApiOperation(value = "get", notes = "Mathod used for get", nickname = "nickname")
    @GetMapping("/public")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(regionService.getAll());
    }


    @ApiOperation(value = "get", notes = "Mathod used for get", nickname = "nickname")
    @GetMapping("/adm/{lang}")
    public ResponseEntity<?> get(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "5") Integer size,
                                 HttpServletRequest request,
                                 @PathVariable("lang") LangEnum lang) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(regionService.getAll(page, size, lang));
    }


    @ApiOperation(value = "getById", notes = "Mathod used for getById", nickname = "nickname")
    @GetMapping("/public")
    public ResponseEntity<?> getById(HttpServletRequest request) {
        Integer id = JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(regionService.getById(id));
    }


    @ApiOperation(value = "update", notes = "Mathod used for update", nickname = "nickname")
    @PutMapping("/adm/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid RegionDTO dto,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("update Region: {}", dto);
        return ResponseEntity.ok(regionService.update(dto, id));
    }


    @ApiOperation(value = "delete", notes = "Mathod used for delete", nickname = "nickname")
    @PutMapping("/adm/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(regionService.delete(id));
    }
}
