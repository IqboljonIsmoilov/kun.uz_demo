package com.company.controller;

import com.company.dto.ArticleTypeDTO;
import com.company.enums.LangEnum;
import com.company.enums.ProfileRole;
import com.company.service.ArticleTypeService;
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
@RequestMapping("/articletype")
public class ArticleTypeController {

    @Autowired
    private ArticleTypeService articleTypeService;
    private Logger log = LoggerFactory.getLogger(ArticleTypeController.class);


    @ApiOperation(value = "create", notes = "Mathod used for create", nickname = "nicname")
    @PostMapping("/adm")
    public ResponseEntity<?> created(@RequestBody @Valid ArticleTypeDTO dto,
                                     HttpServletRequest request) {
        Integer id = JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("created articletype: {}", dto);
        return ResponseEntity.ok(articleTypeService.created(dto, id));
    }


    @ApiOperation(value = "get", notes = "Mathod used for get", nickname = "nicname")
    @GetMapping("/public")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(articleTypeService.getAll());
    }


    @ApiOperation(value = "get", notes = "Mathod used for get", nickname = "nicname")
    @GetMapping("/adm/{lang}")
    public ResponseEntity<?> get(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "5") Integer size,
                                 HttpServletRequest request,
                                 @PathVariable("lang") LangEnum lang) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(articleTypeService.getAll(page, size, lang));
    }


    @ApiOperation(value = "getById", notes = "Mathod used for getById", nickname = "nicname")
    @GetMapping("/public/id")
    public ResponseEntity<?> getById(HttpServletRequest request) {
        Integer id = JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(articleTypeService.getById(id));
    }


    @ApiOperation(value = "update", notes = "Mathod used for update", nickname = "nicname")
    @PutMapping("/adm/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid ArticleTypeDTO dto,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("update articletype: {}", dto);
        return ResponseEntity.ok(articleTypeService.update(dto, id));
    }


    @ApiOperation(value = "delete", notes = "Mathod used for delete", nickname = "nicname")
    @PutMapping("/adm/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(articleTypeService.delete(id));
    }
}
