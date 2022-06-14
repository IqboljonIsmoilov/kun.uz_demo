package com.company.controller;

import com.company.dto.LikeDTO;
import com.company.dto.ProfileJwtDTO;
import com.company.service.LikeService;
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
@RequestMapping("/like")
public class LikeController {


    private final LikeService likeService;


    @ApiOperation(value = "create", notes = "Mathod used for create", nickname = "nicname")
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody @Valid LikeDTO dto,
                                    HttpServletRequest request) {
        Integer pId = JwtUtil.getIdFromHeader(request);
        log.info("create Like: {}{}", dto, LikeController.class);
        return ResponseEntity.ok(likeService.create(dto, pId));
    }


    @ApiOperation(value = "findAllByArticleId", notes = "Mathod used for findAllByArticleId", nickname = "nicname")
    @GetMapping("/article/{id}")
    public ResponseEntity<?> findAllByArticleId(@PathVariable("id") Integer articleId,
                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "3") int size) {
        return ResponseEntity.ok(likeService.listByArticleId(articleId, page, size));
    }


    @ApiOperation(value = "findAllByProfileId", notes = "Mathod used for findAllByProfileId", nickname = "nicname")
    @GetMapping("/adm/profile/{id}")
    public ResponseEntity<?> findAllByProfileId(@PathVariable("id") Integer profileId,
                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "3") int size) {
        return ResponseEntity.ok(likeService.listByProfileId(profileId, page, size));
    }


    @ApiOperation(value = "findAll", notes = "Mathod used for findAll", nickname = "nicname")
    @GetMapping("/adm")
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "3") int size) {
        return ResponseEntity.ok(likeService.list(page, size));
    }


    @ApiOperation(value = "findByProfile", notes = "Mathod used for findByProfile", nickname = "nicname")
    @GetMapping("/profile/{id}")
    public ResponseEntity<?> findByProfile(@PathVariable("id") Integer articleId,
                                           HttpServletRequest request) {
        Integer pId = JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(likeService.getByArticleId(articleId, pId));
    }


    @ApiOperation(value = "delete", notes = "Mathod used for delete", nickname = "nicname")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer commentId,
                                    HttpServletRequest request) {
        ProfileJwtDTO jwtDTO = JwtUtil.getProfileFromHeader(request);
        return ResponseEntity.ok(likeService.delete(commentId, jwtDTO.getId(), jwtDTO.getRole()));
    }
}