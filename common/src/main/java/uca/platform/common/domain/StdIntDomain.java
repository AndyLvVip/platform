package uca.platform.common.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class StdIntDomain extends StdDomain {
    @Id
    private Integer id;
}
