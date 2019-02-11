package uca.platform.common.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class StdStrDomain extends StdDomain {
    @Id
    private String id;
}
