package com.luban.demo.common.exceptions.error;

import lombok.Data;

import java.io.Serializable;


/**
 * 用于校验错误字段描述
 *
 * @author Yang Hao
 * @date 2020/1/12 17:40
 */
@Data
public class FieldVM implements Serializable {
    private String objectName;     //实体名称
    private String field;          //字段
    private String message;        //错误描述
    private String defaultMessage;
    private Object[] arguments;    //参数

    public FieldVM(String objectName, String field, String message, String defaultMessage, Object[] arguments) {
        this.objectName = objectName;
        this.field = field;
        this.message = message;
        this.defaultMessage = defaultMessage;
        this.arguments = arguments;
    }
}