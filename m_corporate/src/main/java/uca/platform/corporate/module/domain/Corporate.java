package uca.platform.corporate.module.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by andy.lv
 * on: 2019/1/24 12:35
 */
@Entity
@Table(name = "corporate")
@Data
public class Corporate {

    @Id
    @Column
    private String id;

    @Column
    private String name;

    @Column
    private Boolean enabled;

    @Column
    private Integer status;

    @Column
    private Integer type;

    @Column
    private Integer version;

    @Column
    private String createdBy;

    @Column
    private LocalDateTime createdOn;

    @Column
    private LocalDateTime updatedOn;

    @Column
    private String updatedBy;
}
