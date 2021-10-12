package com.wanghuiwen.base.config;

import com.wanghuiwen.core.ServiceException;
import com.wanghuiwen.core.response.Result;
import com.wanghuiwen.core.response.ResultEnum;
import com.wanghuiwen.core.response.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.ConnectException;

@ControllerAdvice
public class ExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ResultGenerator resultGenerator;

    @org.springframework.web.bind.annotation.ExceptionHandler()
    public @ResponseBody
    Result defaultErrorHandler(HttpServletRequest req, Exception e) {
        logger.error(req.getRequestURI(), e);
        if (e instanceof MaxUploadSizeExceededException) return resultGenerator.genResult(ResultEnum.UPLOADED_MAX);
        if (e instanceof MissingServletRequestParameterException) return resultGenerator.genResult(ResultEnum.PARAMS_LACK);
        if (e instanceof ConnectException) return resultGenerator.genResult(ResultEnum.CONNECT_EXCEPTION);
        if (e instanceof DuplicateKeyException) return resultGenerator.genResult(ResultEnum.DUPLICATE_KEY);
        if (e instanceof RequestRejectedException) return resultGenerator.genResult(ResultEnum.INTERNAL_SERVER_ERROR);
        if (e instanceof HttpRequestMethodNotSupportedException) return resultGenerator.genExceptionResult(e);
        if (e instanceof ServiceException) return resultGenerator.genExceptionResult(e,((ServiceException) e).getCode());
        if (e instanceof MethodArgumentNotValidException) return resultGenerator.genExceptionResult(e,((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage());
        if (e instanceof BindException) return resultGenerator.genExceptionResult(e,((BindException) e).getAllErrors().get(0).getDefaultMessage());
        return resultGenerator.genExceptionResult(e);
    }
}
