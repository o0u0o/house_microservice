package com.o0u0o.house.api.service;

import com.google.common.collect.Lists;
import com.o0u0o.house.api.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author aiuiot
 * @Date 2020/1/15 10:41 上午
 * @Descripton: 文件服务
 **/
@Service
public class FileService {

    @Value("${file.path}")
    private String filePath;

    public List<String> getImgPaths(List<MultipartFile> files) {
        List<String> paths = Lists.newArrayList();
        files.forEach(file -> {
            File localFile = null;
            try {
                if (!file.isEmpty()) {
                    localFile = FileUtil.saveToLocal(file, filePath);
                    String path =  StringUtils.substringAfterLast(localFile.getAbsolutePath(), filePath);
                    paths.add(path);
                }
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        });
        return paths;

    }
}
