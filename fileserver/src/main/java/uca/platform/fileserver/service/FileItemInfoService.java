package uca.platform.fileserver.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import uca.platform.StdDateUtils;
import uca.platform.exception.InternalServerException;
import uca.platform.fileserver.FileServerConfiguration;
import uca.platform.fileserver.domain.FileItemInfo;
import uca.platform.json.StdObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by andy.lv
 * on: 2019/1/25 17:31
 */
@Service
public class FileItemInfoService {

    private final FileServerConfiguration fileServerConfiguration;
    private final StdObjectMapper stdObjectMapper;
    private final StringRedisTemplate stringRedisTemplate;

    public FileItemInfoService(FileServerConfiguration fileServerConfiguration,
                               StdObjectMapper stdObjectMapper,
                               StringRedisTemplate stringRedisTemplate) {
        this.fileServerConfiguration = fileServerConfiguration;
        this.stdObjectMapper = stdObjectMapper;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public FileItemInfo upload(MultipartFile file, String fileBaseCategory) {
        FileItemInfo fileItemInfo = copy2TempPath(file, fileBaseCategory);
        store2Redis(fileItemInfo);
        return fileItemInfo;
    }


    private void store2Redis(FileItemInfo fileItemInfo) {
        this.stringRedisTemplate.opsForValue().set(fileItemInfo.getId(), stdObjectMapper.toJson(fileItemInfo));
    }

    private FileItemInfo copy2TempPath(MultipartFile file, String fileBaseCategory) {
        String filename = file.getOriginalFilename();
        String fileExtension = "";
        int position;
        if (-1 != (position = filename.lastIndexOf("."))) {
            fileExtension = filename.substring(position);
        }
        FileItemInfo fileItemInfo = new FileItemInfo();
        fileItemInfo.setId(UUID.randomUUID().toString());
        String relativeFilePath = Paths.get(fileBaseCategory, StdDateUtils.now2yyyyMMdd(), fileItemInfo.getId() + fileExtension).toString();
        fileItemInfo.setFilePath(this.fileServerConfiguration.getUrl() + "/" + relativeFilePath.replace("\\", "/"));
        fileItemInfo.setFileName(file.getOriginalFilename());
        Path tempFilePath = Paths.get(fileServerConfiguration.getTempFilePath(), relativeFilePath);
        try {
            File dest = new File(tempFilePath.toString());
            if(!dest.getParentFile().exists())
                Assert.isTrue(dest.getParentFile().mkdirs(), "can not mkdirs for: " + dest.getParentFile().getAbsolutePath());
            Assert.isTrue(dest.createNewFile(), "can not mkdirs for: " + dest.getAbsolutePath());
            file.transferTo(dest);
            fileItemInfo.setSize(file.getSize());
            return fileItemInfo;
        } catch (IOException e) {
            throw new InternalServerException(e);
        }
    }
}
