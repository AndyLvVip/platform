package uca.platform.fileserver.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uca.platform.StdStringUtils;
import uca.platform.fileserver.FileServerConfiguration;
import uca.platform.fileserver.StoreLocationFlag;
import uca.platform.fileserver.domain.FileItemInfo;
import uca.platform.fileserver.repository.FileItemInfoRepository;

/**
 * Created by andy.lv
 * on: 2019/1/25 17:31
 */
@Service
public class FileItemInfoService {

    private final FileServerConfiguration fileServerConfiguration;
    private final FileItemInfoRepository fileItemInfoRepository;
    private final FileSetInfoService fileSetInfoService;
    private final FileUploadService fileUploadService;

    public FileItemInfoService(FileServerConfiguration fileServerConfiguration
            , FileItemInfoRepository fileItemInfoRepository
            , FileSetInfoService fileSetInfoService
            , FileUploadService fileUploadService
    ) {
        this.fileUploadService = fileUploadService;
        this.fileItemInfoRepository = fileItemInfoRepository;
        this.fileServerConfiguration = fileServerConfiguration;
        this.fileSetInfoService = fileSetInfoService;
    }

    private FileItemInfo store(MultipartFile file, StoreLocationFlag flag) {
        String relativeFilePath = fileUploadService.store(file, flag);
        FileItemInfo fileItemInfo = new FileItemInfo();
        fileItemInfo.setId(StdStringUtils.uuid());
        fileItemInfo.setFilePath(this.fileUploadService.buildFilePath(relativeFilePath));
        fileItemInfo.setFileName(file.getOriginalFilename());
        fileItemInfo.setSize(file.getSize());
        return fileItemInfo;
    }

    public FileItemInfo upload(
            StoreLocationFlag flag
            , String fileSetInfoId
            , MultipartFile file
            , String fileSrcRemark
            , String createdBy
    ) {
        fileSetInfoService.initFileSetInfoIfNotExists(fileSetInfoId, fileSrcRemark, createdBy);
        FileItemInfo fileItemInfo = store(file, flag);
        fileItemInfo.setFileSetInfoId(fileSetInfoId);
        fileItemInfoRepository.insert(fileItemInfo, createdBy);
        return fileItemInfo;
    }

    public void delete(FileItemInfo fileItemInfo) {
        FileItemInfo result = fileItemInfoRepository.forceFindById(fileItemInfo.getId());
        result.setVersion(fileItemInfo.getVersion());
        fileItemInfoRepository.delete(result);
    }

}
