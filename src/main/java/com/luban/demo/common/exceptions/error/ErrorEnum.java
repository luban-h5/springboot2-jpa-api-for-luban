package com.luban.demo.common.exceptions.error;

public enum ErrorEnum {

    METHOD_ARGUMENT_NOT_VALID("error.common.argumentNotValid", "方法参数无效"),
    ACCESS_DENIED("error.common.accessDenied", "进入拒绝"),
    INTERNAL_SERVER_ERROR("error.common.internalServerError", "服务内部错误"),
    ERR_METHOD_NOT_SUPPORTED("error.common.methodNotSupported", "方法不支持"),
    UNAUTHORIZED("error.common.unauthorized", "未授权"),
    BAD_CREDENTIALS("error.common.badCredentials", "账号或密码错误"),
    HTTP_MEDIA_TYPE_NOT_SUPPORTED("error.common.mediaTypeNotSupported", "请求Content-Type不支持"),
    UN_PROCESSABLE_ENTITY("error.common.unProcessableEntity", "无法处理的实体类"),
    NAME_PASSWORD_INCORRECT("user.username.password.incorrect", "用户名或密码错误"),
    USER_NOT_EXIST("user.notExist", "用户不存在"),
    USER_LOCKED("user.lock", "用户已被锁定");

    private String error;
    private String desc;

    ErrorEnum(String error, String desc) {
        this.error = error;
        this.desc = desc;
    }

    public String getError() {
        return this.error;
    }

    public String getDesc() {
        return this.desc;
    }

}
