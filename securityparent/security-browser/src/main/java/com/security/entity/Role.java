package com.security.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author heartccace
 * @create 2020-05-21 16:49
 * @Description TODO
 * @Version 1.0
 */
@Entity
@Table(name="tb_role")
@Data
public class Role implements Serializable {
    @Id
    private Integer id;
    private String name;
    private String nameZh;
}
