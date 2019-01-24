package uca.platform.fileserver.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uca.platform.fileserver.domain.FileSetInfo;
import uca.platform.fileserver.repository.FileSetInfoRepository;

/**
 * Created by andy.lv
 * on: 2019/1/24 17:16
 */
@Service
public class FileSetInfoService {

    FileSetInfoRepository fileSetInfoRepository;

    public FileSetInfoService(FileSetInfoRepository fileSetInfoRepository) {
        this.fileSetInfoRepository = fileSetInfoRepository;
    }

    @Transactional
    public FileSetInfo create(FileSetInfo fileSetInfo, String createdBy) {
        FileSetInfo result = new FileSetInfo();
        result.setFileSrcName(fileSetInfo.getFileSrcName());
        fileSetInfoRepository.create(result, createdBy);
        return result;
    }

    public FileSetInfo fetch(String id) {
        return fileSetInfoRepository.findById(id);
    }
}
