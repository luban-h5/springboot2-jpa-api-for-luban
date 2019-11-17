package com.luban.demo.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Yang Hao
 * @date 2019/11/17 21:48
 */
@Data
public class WorkDto {

    private Long id;

    private String title;

    private String description;

    private String coverImageUrl;

    private List<Object> pages;

    private Date createTime;

    private Date updateTime;

    private boolean publish;

    private boolean template;
}
