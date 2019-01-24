package uca.platform.fileserver.domain;

import lombok.Data;
import uca.platform.common.domain.StdDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by andy.lv
 * on: 2019/1/24 15:52
 */
@Entity
@Table(name = "file_item_info")
@Data
public class FileItemInfo extends StdDomain {

    @Column
    private String fileSetInfoId;

    @Column
    private String filePath;

    @Column
    private String fileName;

    @Column
    private Long size;

    @Column
    private Integer sequence;
}
