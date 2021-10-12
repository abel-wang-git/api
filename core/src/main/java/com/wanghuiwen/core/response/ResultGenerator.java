package com.wanghuiwen.core.response;


import com.wanghuiwen.common.JSONUtils;
import com.wanghuiwen.core.model.ValidatedError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 响应结果生成工具
 */
@Component
public class ResultGenerator {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MessageSource messageSource;
    public Result genSuccessResult() {
        return new Result(ResultEnum.SUCCESS_MESSAGE,null).setMessage(messageSource.getMessage("200", null, LocaleContextHolder.getLocale()));
    }

    public Result genSuccessResult(Object data) {
        return new Result(ResultEnum.SUCCESS_MESSAGE,data).setMessage(messageSource.getMessage("200", null, LocaleContextHolder.getLocale()));
    }

    public Result genSuccessResult(IResultEnum resultEnum,Object data) {
        return new Result()
                .setCode(200)
                .setMessage(messageSource.getMessage(resultEnum.getMessageCode(), null, LocaleContextHolder.getLocale()))
                .setData(data);
    }

    public Result genFailResult(IResultEnum resultEnum) {
        return new Result(resultEnum).setMessage(messageSource.getMessage(resultEnum.getMessageCode(), null, LocaleContextHolder.getLocale()));
    }

    public Result genResult(IResultEnum resultEnum) {
        return new Result(resultEnum).setMessage(messageSource.getMessage(resultEnum.getMessageCode(), null, LocaleContextHolder.getLocale()));
    }

    public Result genExceptionResult(Exception e) {
        return new Result(e);
    }

    public Result genExceptionResult(Exception e,String code) {
        /**
         * 参数校验处理
         */
        if (e instanceof BindException){
            logger.info(JSONUtils.obj2json(((BindException) e).getAllErrors()));
            List<ValidatedError> errors = new ArrayList<>();
            for (ObjectError allError : ((BindException) e).getAllErrors()) {
                ValidatedError error = new ValidatedError();
                error.setField(allError.getObjectName());
                error.setMessage(messageSource.getMessage(code, null, LocaleContextHolder.getLocale()));
                errors.add(error);
            }
            Result result = new Result();
            result.setCode(ResultEnum.PARAMS_VALIDATED_ERROR.getCode());
            result.setData(errors);
            result.setMessage(errors.get(0).getMessage());
            return result;
        }
        return new Result(e).setMessage(messageSource.getMessage(code, null, LocaleContextHolder.getLocale()));
    }
}
