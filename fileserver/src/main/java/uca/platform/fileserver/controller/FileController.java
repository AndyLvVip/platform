package uca.platform.fileserver.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uca.platform.fileserver.StoreLocationFlag;
import uca.platform.fileserver.domain.FileItemInfo;
import uca.platform.fileserver.service.FileUploadService;

@RestController
public class FileController {

    private final FileUploadService fileUploadService;

    public FileController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/file")
    public FileItemInfo upload(MultipartFile file) {
        String relativeFilePath = fileUploadService.store(file, StoreLocationFlag.RICH_TEXT_FILE);
        String filePath = fileUploadService.buildFilePath(relativeFilePath);
        FileItemInfo result = new FileItemInfo();
        result.setFilePath(filePath);
        return result;
    }
}
