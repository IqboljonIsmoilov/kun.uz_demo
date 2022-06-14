package com.company.controller;

import com.company.dto.TagDTO;
import com.company.enums.LangEnum;
import com.company.enums.ProfileRole;
import com.company.service.TagService;
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
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;


    @ApiOperation(value = "create", notes = "Mathod used for create")
    @PostMapping("/adm")
    public ResponseEntity<?> created(@RequestBody @Valid TagDTO requestDTO,
                                     HttpServletRequest request) {
        Integer id = JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("created Tag: {}{}", requestDTO, TagController.class);
        return ResponseEntity.ok(tagService.created(requestDTO, id));
    }


    @ApiOperation(value = "get", notes = "Mathod used for get")
    @GetMapping("/public")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(tagService.getAll());
    }


    @ApiOperation(value = "get", notes = "Mathod used for get")
    @GetMapping("/adm/{lang}")
    public ResponseEntity<?> get(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "5") Integer size,
                                 HttpServletRequest request,
                                 @PathVariable("lang") LangEnum lang) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(tagService.getAll(page, size, lang));
    }


    @ApiOperation(value = "get By Id", notes = "Mathod used for get By Id")
    @GetMapping("/public")
    public ResponseEntity<?> getById(HttpServletRequest request) {
        Integer id = JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(tagService.getById(id));
    }


    @ApiOperation(value = "update", notes = "Mathod used for update")
    @PutMapping("/adm/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid TagDTO updateDTO,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("update Tag: {}{}", updateDTO, TagController.class);
        return ResponseEntity.ok(tagService.update(updateDTO, id));
    }


    @ApiOperation(value = "delete", notes = "Mathod used for delete")
    @PutMapping("/adm/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(tagService.delete(id));
    }
}