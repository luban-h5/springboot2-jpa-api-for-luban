package com.luban.demo.service.impl;

import com.luban.demo.common.enums.UploadEnum;
import com.luban.demo.common.exceptions.LubanException;
import com.luban.demo.common.utils.EnvUtil;
import com.luban.demo.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * FIXME 是否需要文件服务器
 *
 * @author Yang Hao
 * @date 2020/1/12 18:09
 */
@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    @Resource
    private Environment env;
    private String ftpHost;
    private Integer ftpPort;
    private String username;
    private String password;
    private String localDir;

    @PostConstruct
    public void init() {
        ftpHost = EnvUtil.getStrWithCache(env, "upload.ftp.host", "127.0.0.1");
        ftpPort = EnvUtil.getIntWithCache(env, "upload.ftp.port", 21);
        username = EnvUtil.getStrWithCache(env, "upload.ftp.username", "username");
        password = EnvUtil.getStrWithCache(env, "upload.ftp.password", "password");
        localDir = EnvUtil.getStrWithCache(env, "upload.local.dir", "/opt");
    }


    @Override
    public void upload(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new LubanException("请上传文件");
        }

        try {
            Path path = getLocalPath(localDir, file.getOriginalFilename());
            path = Files.createDirectories(path);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            log.info("software upload save to local success");
        } catch (Exception e) {
            log.error("software upload save to local failed");
        }

        //FIXME 是否需要上传服务器,本地已保存
        FTPClient ftpClient = new FTPClient();
        try {
            boolean ok = conectFTP(ftpClient);
            if (!ok) {
                log.error("ftp login failed,username:{},password:{}", username, password);
                return;
            }
            log.info("ftp login success");
            changeDirectory(ftpClient);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            String localPathString = getLocalPathString(localDir, file.getOriginalFilename());
            InputStream inputStream = new FileInputStream(new File(localPathString));
            boolean uploaded = ftpClient.storeFile(file.getOriginalFilename(), inputStream);
            inputStream.close();

        } catch (IOException e) {
            log.error("software upload file:{} to ftp server failed", file);
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.logout();
                    ftpClient.disconnect();
                } catch (IOException e) {
                    log.error("ftp client disconnect error");
                }
            }
        }
    }


    /**
     * 切换文件夹
     *
     * @param ftpClient
     * @throws IOException
     */
    private void changeDirectory(FTPClient ftpClient) throws IOException {
        if (!ftpClient.changeWorkingDirectory(UploadEnum.work.name())) {
            log.info("ftp create ota dir：{}", UploadEnum.work.name());
            ftpClient.makeDirectory(UploadEnum.work.name());
        }
        ftpClient.changeWorkingDirectory(UploadEnum.work.name());
    }


    /**
     * ftp连接
     *
     * @param ftpClient
     * @return
     * @throws IOException
     */
    private boolean conectFTP(FTPClient ftpClient) throws IOException {
        ftpClient.connect(ftpHost, ftpPort);
        ftpClient.enterLocalPassiveMode();
        return ftpClient.login(username, password);
    }

    public Path getLocalPath(String localDir, String file) {
        return Paths.get(getLocalPathString(localDir, file));
    }

    public String getLocalPathString(String localDir, String file) {
        StringBuilder sb = new StringBuilder();
        sb.append(localDir);
        sb.append("/");
        sb.append(file);
        return sb.toString();
    }


}
