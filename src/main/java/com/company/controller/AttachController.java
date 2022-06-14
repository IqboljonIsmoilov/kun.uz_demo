package com.company.controller;

import com.company.dto.AttachDTO;
import com.company.enums.ProfileRole;
import com.company.service.AttachService;
import com.company.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/attach")
public class AttachController {

    private final AttachService attachService;


    @ApiOperation(value = "create", notes = "Mathod used for create")
    @PostMapping("/upload")
    public ResponseEntity<AttachDTO> create(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(attachService.upload(file));
    }


    @ApiOperation(value = "open", notes = "Mathod used for open")
    @GetMapping(value = "/open/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] open(@PathVariable("fileName") String fileName) {
        return attachService.open(fileName);
    }


    @ApiOperation(value = "open_general", notes = "Mathod used for open_general")
    @GetMapping(value = "/open_general/{fileName}", produces = MediaType.ALL_VALUE)
    public byte[] open_general(@PathVariable("fileName") String fileName) {
        return attachService.open(fileName);
    }


    @ApiOperation(value = "download", notes = "Mathod used for download")
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName) {
        return attachService.download(fileName);
    }


    @ApiOperation(value = "list", notes = "Mathod used for list")
    @GetMapping("/adm/list")
    public ResponseEntity<?> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "3") int size,
                                  HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(attachService.paginationList(page, size));
    }


    @ApiOperation(value = "delete", notes = "Mathod used for delete")
    @DeleteMapping("/adm/{key}")
    public ResponseEntity<?> delete(@PathVariable("key") String key,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(attachService.delete(key));
    }
}