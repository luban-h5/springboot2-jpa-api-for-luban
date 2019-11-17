package com.luban.demo.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Yang Hao
 * @date 2019/11/17 20:36
 */
@Data
@Entity
@Table(name = "work")
public class WorkForms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20) AUTO_INCREMENT NOT NULL COMMENT '主键'")
    private Long id;

    @Column(name = "form")
    private String form;

    @Column(name = "work_id")
    private Long workId;
}
