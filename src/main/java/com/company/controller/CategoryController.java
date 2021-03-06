package com.company.controller;

import com.company.dto.CategoryDTO;
import com.company.enums.LangEnum;
import com.company.enums.ProfileRole;
import com.company.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @ApiOperation(value = "create", notes = "Mathod used for create")
    @PostMapping("/adm/create")
    public ResponseEntity<?> created(@RequestBody @Valid CategoryDTO requestDTO,
                                     HttpServletRequest request) {
        Integer id = JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("created Category: {}{}", requestDTO, CategoryController.class);
        return ResponseEntity.ok(categoryService.created(requestDTO, id));
    }


    @ApiOperation(value = "get", notes = "Mathod used for get")
    @GetMapping("/public")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(categoryService.getAll());
    }


    @ApiOperation(value = "get", notes = "Mathod used for get")
    @GetMapping("/adm/{lang}")
    public ResponseEntity<?> get(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "5") Integer size,
                                 HttpServletRequest request,
                                 @PathVariable("lang") LangEnum lang) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(categoryService.getAll(page, size, lang));
    }


    @ApiOperation(value = "get By Id", notes = "Mathod used for getById")
    @GetMapping("/public/id")
    public ResponseEntity<?> getById(HttpServletRequest request) {
        Integer id = JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(categoryService.getById(id));
    }


    @ApiOperation(value = "update", notes = "Mathod used for update")
    @PutMapping("/adm/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid CategoryDTO updateDTO,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("update Category: {}{}", updateDTO, CategoryController.class);
        return ResponseEntity.ok(categoryService.update(updateDTO, id));
    }


    @ApiOperation(value = "delete", notes = "Mathod used for delete")
    @DeleteMapping("/adm/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(categoryService.delete(id));
    }
}