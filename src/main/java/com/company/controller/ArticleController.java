package com.company.controller;

import com.company.dto.ArticleDTO;
import com.company.enums.LangEnum;
import com.company.enums.ProfileRole;
import com.company.service.ArticleService;
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
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {


    private final ArticleService articleService;


    @ApiOperation(value = "create", notes = "Mathod used for create", nickname = "nicname")
    @PostMapping("/adm")
    public ResponseEntity<?> create(@RequestBody @Valid ArticleDTO dto,
                                    HttpServletRequest request) {
        Integer pId = JwtUtil.getIdFromHeader(request, ProfileRole.MODERATOR);
        log.info("create Article: {}{}", dto, ArticleController.class);
        return ResponseEntity.ok(articleService.create(dto, pId));
    }


    @ApiOperation(value = "create", notes = "Mathod used for create", nickname = "nicname")
    @GetMapping("/public/list")
    public ResponseEntity<?> listByLang(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(articleService.list(page, size));
    }


    @ApiOperation(value = "create", notes = "Mathod used for create", nickname = "nicname")
    @GetMapping("/public/region/{id}")
    public ResponseEntity<?> listByRegion(@PathVariable("id") Integer id,
                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(articleService.listRegionById(id, page, size));
    }


    @ApiOperation(value = "create", notes = "Mathod used for create", nickname = "nicname")
    @GetMapping("/public/category/{id}")
    public ResponseEntity<?> listByCategory(@PathVariable("id") Integer id,
                                            @RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(articleService.listCategoryById(id, page, size));
    }


    @ApiOperation(value = "create", notes = "Mathod used for create", nickname = "nicname")
    @GetMapping("/public/type/{id}")
    public ResponseEntity<?> getByType(@PathVariable("id") Integer typeId) {
        return ResponseEntity.ok(articleService.getArticleByType(typeId));
    }


    @ApiOperation(value = "create", notes = "Mathod used for create", nickname = "nicname")
    @GetMapping("/public/public/{id}")
    public ResponseEntity<?> getPublic(@PathVariable("id") Integer id,
                                       @RequestHeader(name = "Accepted-Language", defaultValue = "uz") LangEnum lang) {
        return ResponseEntity.ok(articleService.getPublic(id, lang));
    }


    @ApiOperation(value = "create", notes = "Mathod used for create", nickname = "nicname")
    @PutMapping("/adm/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid ArticleDTO dto,
                                    HttpServletRequest request) {
        Integer pId = JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("update Article: {}{}", dto, ArticleController.class);
        return ResponseEntity.ok(articleService.update(id, dto, pId));
    }


    @ApiOperation(value = "create", notes = "Mathod used for create", nickname = "nicname")
    @DeleteMapping("/adm/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(articleService.delete(id));
    }


    @ApiOperation(value = "create", notes = "Mathod used for create", nickname = "nicname")
    @PutMapping("/adm/published/{id}")
    public ResponseEntity<?> published(@PathVariable("id") Integer id,
                                       HttpServletRequest request) {
        Integer pid = JwtUtil.getIdFromHeader(request, ProfileRole.SUPERMODERATOR);
        return ResponseEntity.ok(articleService.published(id, pid));
    }


    @ApiOperation(value = "create", notes = "Mathod used for create", nickname = "nicname")
    @PutMapping("/adm/blocked/{id}")
    public ResponseEntity<?> blocked(@PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        Integer pid = JwtUtil.getIdFromHeader(request, ProfileRole.SUPERMODERATOR);
        return ResponseEntity.ok(articleService.blocked(id, pid));
    }
}
