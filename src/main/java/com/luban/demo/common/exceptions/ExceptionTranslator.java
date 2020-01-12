package com.luban.demo.common.exceptions;


import com.luban.demo.common.exceptions.error.ErrorEnum;
import com.luban.demo.common.exceptions.error.ErrorVM;
import com.luban.demo.common.exceptions.error.FieldVM;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures.
 */
@ControllerAdvice
public class ExceptionTranslator extends AbstractExceptionTranslator {


    @Override
    public ErrorVM getErrorVM(BindingResult bindingResult) {
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
        if (!CollectionUtils.isEmpty(fieldErrors)) {
            String errorCode = fieldErrors.get(0).getCode();
            String message = fieldErrors.get(0).getDefaultMessage();
            return new ErrorVM(errorCode, message, null, HttpStatus.BAD_REQUEST.value(), fieldVMS);
        }
        String errorCode = ErrorEnum.METHOD_ARGUMENT_NOT_VALID.getError();
        return new ErrorVM(errorCode, ErrorEnum.METHOD_ARGUMENT_NOT_VALID.getDesc(), null, HttpStatus.BAD_REQUEST.value(), fieldVMS);
    }
}
