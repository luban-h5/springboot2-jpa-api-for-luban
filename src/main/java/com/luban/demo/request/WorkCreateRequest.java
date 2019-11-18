package com.luban.demo.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Yang Hao
 * @date 2019/11/17 21:07
 */
@Data
public class WorkCreateRequest {

    private String title;

    private String description;

    private String coverImageUrl;

    private List<Object> pages;

    private Date createTime = new Date();

    private Date updateTime = new Date();

    private boolean publish = false;

    private boolean template = false;
}
