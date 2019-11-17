package com.luban.demo.domain;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import springfox.documentation.spring.web.json.Json;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Yang Hao
 * @date 2019/11/17 19:51
 */
@Data
@Entity
@Table(name = "work")
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(11) AUTO_INCREMENT NOT NULL COMMENT '主键'")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @Column(name = "pages")
    private String pages;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "publish")
    private boolean publish;

    @Column(name = "template")
    private boolean template;

}
