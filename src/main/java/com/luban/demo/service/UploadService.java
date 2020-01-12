package com.luban.demo.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Yang Hao
 * @date 2020/1/12 18:09
 */
public interface UploadService {
    /**
     * 上传文件
     *
     * @param file
     */
    void upload(MultipartFile file);
}
