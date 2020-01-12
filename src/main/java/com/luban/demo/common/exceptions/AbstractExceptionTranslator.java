package com.luban.demo.common.exceptions;


import com.luban.demo.common.exceptions.error.ErrorEnum;
import com.luban.demo.common.exceptions.error.ErrorVM;
import com.luban.demo.common.exceptions.error.FieldVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures.
 */
public abstract class AbstractExceptionTranslator {
    private final Logger log = LoggerFactory.getLogger(AbstractExceptionTranslator.class);


    /**
     * 处理参数校验异常，多个字段错误转换成错误数组
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorVM processValidationError(HttpServletRequest request, MethodArgumentNotValidException ex) {
        ErrorVM error = getErrorVM(ex.getBindingResult());
        log.error("MethodArgumentNotValidException : [" + error.getErrorId() + "] : ", error.getError(), ex);
        return error;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    protected ErrorVM processAccessDeniedException(AccessDeniedException ex) {
        String errorCode = ErrorEnum.ACCESS_DENIED.getError();
        ErrorVM error = new ErrorVM(errorCode, ex.getMessage(), HttpStatus.FORBIDDEN.value());
        error.setCause(ex.getMessage());
        log.error("AccessDeniedException[" + error.getErrorId() + "]: " + ex.getMessage(), ex);
        return error;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    protected ErrorVM processMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        String errorCode = ErrorEnum.ERR_METHOD_NOT_SUPPORTED.getError();
        ErrorVM error = new ErrorVM(errorCode, ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED.value());
        log.error("HttpRequestMethodNotSupportedException[" + error.getErrorId() + "]: " + ex.getMessage(), ex);
        return error;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ErrorVM handleIllegalArgumentException(IllegalArgumentException ex) {
        String errorCode = ErrorEnum.UN_PROCESSABLE_ENTITY.getError();
        ErrorVM error = new ErrorVM(errorCode, ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        log.error("IllegalArgumentException[" + error.getErrorId() + "]: " + ex.getMessage(), ex);
        return error;
    }

    @ExceptionHandler(LubanException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorVM processServiceError(LubanException ex) {
        ErrorVM error = new ErrorVM(ex.getError(), ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        log.error("AconnException[" + error.getErrorId() + "]: " + ex.getMessage(), ex);
        return error;
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorVM processBindException(BindException ex) {
        ErrorVM error = getErrorVM(ex.getBindingResult());
        log.error("BindException[" + error.getErrorId() + "]: " + ex.getMessage(), ex);
        return error;
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorVM handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        String errorCode = ErrorEnum.HTTP_MEDIA_TYPE_NOT_SUPPORTED.getError();
        ErrorVM error = new ErrorVM(errorCode,
                ErrorEnum.HTTP_MEDIA_TYPE_NOT_SUPPORTED.getDesc(),
                HttpStatus.BAD_REQUEST.value());
        log.error("HttpMediaTypeNotSupportedException[" + error.getErrorId() + "]: " + ex.getMessage(), ex);
        return error;
    }


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorVM> processException(Exception ex) {
        BodyBuilder builder;
        ErrorVM error;
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            builder = ResponseEntity.status(responseStatus.value());
            String errorCode = "error." + responseStatus.value().value();
            error = new ErrorVM(errorCode, ex.getMessage(), responseStatus.reason(), responseStatus.value().value());
        } else {
            builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            String errorCode = ErrorEnum.INTERNAL_SERVER_ERROR.getError();
            error = new ErrorVM(errorCode, ErrorEnum.INTERNAL_SERVER_ERROR.getDesc());
            error.setCause(ex.getMessage());
        }
        log.error("Exception[" + error.getErrorId() + "]: " + ex.getMessage(), ex);
        return builder.body(error);
    }


    protected ErrorVM getErrorVM(BindingResult bindingResult) {
        BindingResult result = bindingResult;
        List<FieldError> fieldErrors = result.getFieldErrors();

        List<FieldVM> fieldVMS = new ArrayList(fieldErrors.size());

        for (FieldError fieldError : fieldErrors) {
            String objectName = fieldError.getObjectName();
            String field = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();
            String code = fieldError.getCode();
            fieldVMS.add(new FieldVM(objectName, field, code, defaultMessage, null));
        }
        String errorCode = ErrorEnum.METHOD_ARGUMENT_NOT_VALID.getError();
        return new ErrorVM(errorCode, errorCode, null, HttpStatus.BAD_REQUEST.value(), fieldVMS);
    }


}
