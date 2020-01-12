package com.luban.demo.common.exceptions.error;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 用于报错后错误返回
 */
@Data
public class ErrorVM implements Serializable {
    private String errorId;
    private String entityName;     //错误实体
    private String error;          //错误
    private String cause;          //错误原因
    private Integer status;        //错误状态码
    private String message;    //错误信息
    private List<FieldVM> fields;  //错误字段

    public ErrorVM(String error, String description) {
        this(error, description, null);
    }

    public ErrorVM(String error, String description, Integer status) {
        this(error, description, null, status, null);
    }

    public ErrorVM(String error, String description, String cause, Integer status) {
        this(error, description, cause, status, null);
    }

    public ErrorVM(String error, String message, String cause, Integer status, List<FieldVM> fields) {
        this.errorId = RandomStringUtils.randomNumeric(8);
        this.error = error;
        this.message = message;
        this.status = status;
        this.fields = fields;
        this.cause = cause;
//        this.entityName = this.getClass().getSimpleName();
    }


}
