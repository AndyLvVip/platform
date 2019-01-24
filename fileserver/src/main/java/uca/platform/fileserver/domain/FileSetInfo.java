package uca.platform.fileserver.domain;

import lombok.Data;
import lombok.ToString;
import uca.platform.common.domain.StdDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by andy.lv
 * on: 2019/1/24 16:18
 */
@Entity
@Table(name = "file_set_info")
@Data
@ToString(callSuper = true)
public class FileSetInfo extends StdDomain {

    @Column(name = "file_src_name")
    private String fileSrcName;

}
