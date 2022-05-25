package com.company.controller;

import com.company.dto.TagDTO;
import com.company.enums.LangEnum;
import com.company.enums.ProfileRole;
import com.company.service.TagService;
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
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;
    private Logger log = LoggerFactory.getLogger(TagController.class);


    @ApiOperation(value = "create", notes = "Mathod used for create", nickname = "nickname")
    @PostMapping("/adm/created")
    public ResponseEntity<?> created(@RequestBody @Valid TagDTO dto,
                                     HttpServletRequest request) {
        Integer id = JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("created Tag: {}", dto);
        return ResponseEntity.ok(tagService.created(dto, id));
    }


    @ApiOperation(value = "get", notes = "Mathod used for get", nickname = "nickname")
    @GetMapping("/public")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(tagService.getAll());
    }


    @ApiOperation(value = "get", notes = "Mathod used for get", nickname = "nickname")
    @GetMapping("/adm/{lang}")
    public ResponseEntity<?> get(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "5") Integer size,
                                 HttpServletRequest request,
                                 @PathVariable("lang") LangEnum lang) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(tagService.getAll(page, size, lang));
    }


    @ApiOperation(value = "getById", notes = "Mathod used for getById", nickname = "nickname")
    @GetMapping("/public/id")
    public ResponseEntity<?> getById(HttpServletRequest request) {
        Integer id = JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(tagService.getById(id));
    }


    @ApiOperation(value = "update", notes = "Mathod used for update", nickname = "nickname")
    @PutMapping("/adm/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid TagDTO dto,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("update Tag: {}", dto);
        return ResponseEntity.ok(tagService.update(dto, id));
    }


    @ApiOperation(value = "delete", notes = "Mathod used for delete", nickname = "nickname")
    @PutMapping("/adm/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(tagService.delete(id));
    }
}
