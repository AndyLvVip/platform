package uca.platform.fileserver.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uca.platform.StdStringUtils;
import uca.platform.fileserver.StoreLocationFlag;
import uca.platform.fileserver.domain.FileItemInfo;
import uca.platform.fileserver.service.FileItemInfoService;
import uca.platform.fileserver.service.ImageFileItemInfoService;

/**
 * Created by andy.lv
 * on: 2019/1/25 17:28
 */
@RestController
public class FileItemController {

    private final FileItemInfoService fileItemInfoService;

    private final ImageFileItemInfoService imageFileItemInfoService;

    public FileItemController(FileItemInfoService fileItemInfoService
                              , ImageFileItemInfoService imageFileItemInfoService
    ) {
        this.fileItemInfoService = fileItemInfoService;
        this.imageFileItemInfoService = imageFileItemInfoService;
    }

    /**
     * @param user
     * @param fileSetInfoId
     * @param file
     * @param fileSrcRemark
     * @return
     */
    @PostMapping("/fileItem/file")
    public FileItemInfo upload(
            @AuthenticationPrincipal User user
            , String fileSetInfoId
            , MultipartFile file
            , String fileSrcRemark
    ) {
        if(StringUtils.isEmpty(fileSetInfoId))
            fileSetInfoId = StdStringUtils.uuid();
        return this.fileItemInfoService.upload(StoreLocationFlag.FILE, fileSetInfoId, file, fileSrcRemark, user.getUsername());
    }


    @DeleteMapping("/fileItem/file/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id, @RequestBody FileItemInfo fileItemInfo) {
        fileItemInfo.setId(id);
        this.fileItemInfoService.delete(fileItemInfo);
    }

    /**
     * @param user
     * @param fileSetInfoId
     * @param file
     * @param fileSrcRemark
     * @return
     */
    @PostMapping("/fileItem/img")
    public FileItemInfo uploadImg(
            @AuthenticationPrincipal User user
            , String fileSetInfoId
            , MultipartFile file
            , String fileSrcRemark
    ) {
        if(StringUtils.isEmpty(fileSetInfoId))
            fileSetInfoId = StdStringUtils.uuid();
        return this.imageFileItemInfoService.upload(fileSetInfoId, file, fileSrcRemark, user.getUsername());
    }
}
