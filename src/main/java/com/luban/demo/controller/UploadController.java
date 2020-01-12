package com.luban.demo.controller;

import com.luban.demo.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @author Yang Hao
 * @date 2020/1/12 17:40
 */
@RestController
@RequestMapping(value = "/upload")
@Api(value = "文件上传", tags = {"文件上传"})
@Slf4j
public class UploadController {

    @Resource
    private UploadService uploadService;

    @ApiOperation(value = "文件上传")
    @PostMapping
    public void upload(@NotNull @RequestParam(value = "file", required = true) MultipartFile file) {
        uploadService.upload(file);
    }

}
