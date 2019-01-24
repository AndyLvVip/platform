package uca.platform.common.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Created by andy.lv
 * on: 2019/1/24 16:11
 */
@MappedSuperclass
@Data
public class StdDomain {
    @Column
    @Id
    private String id;

    @Column
    private Integer version;

    @Column
    private LocalDateTime createdOn;

    @Column
    private String createdBy;

    @Column
    private LocalDateTime updatedOn;

    @Column
    private String updatedBy;
}
