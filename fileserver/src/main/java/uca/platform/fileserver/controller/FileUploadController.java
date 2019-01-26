package uca.platform.fileserver.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uca.platform.fileserver.domain.FileItemInfo;
import uca.platform.fileserver.service.FileItemInfoService;

/**
 * Created by andy.lv
 * on: 2019/1/25 17:28
 */
@RestController
public class FileUploadController {

    private final FileItemInfoService fileItemInfoService;

    public FileUploadController(FileItemInfoService fileItemInfoService) {
        this.fileItemInfoService = fileItemInfoService;
    }

    @PostMapping("/file/upload")
    public FileItemInfo upload(MultipartFile file, String fileBaseCategory) {
        return this.fileItemInfoService.upload(file, fileBaseCategory);
    }

}
