package uca.platform.common.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Created by andy.lv
 * on: 2019/1/24 16:11
 */
@MappedSuperclass
@Data
public class StdDomain {
    @Column(name = "version")
    private Integer version;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "updated_by")
    private String updatedBy;
}
