package com.wanghuiwen.base.config.log;


import com.wanghuiwen.base.model.SysLog;
import com.wanghuiwen.base.service.SysLogService;
import com.wanghuiwen.common.JSONUtils;
import com.wanghuiwen.core.config.AuthUser;
import com.wanghuiwen.core.controller.Ctrl;
import io.swagger.annotations.Api;
import io.swagger.models.HttpMethod;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志切面
 */

@Aspect
@Component
public class LogAspect extends Ctrl {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Resource
    private SysLogService sysLogService;

    /**
     * 统计时间
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(* com.wanghuiwen.core.controller.IControllerLog+.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("################################: 开始请求 :###################################");
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        SysLog log = new SysLog();

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        AuthUser info = null;
        try {
            info =  getAuthUser(authentication);
        }catch (Exception e){
            logger.error("auth user "+ authentication.getPrincipal());
        }
        if(info!=null){
            log.setUserId(info.getId());
        }else {
            log.setUserId(0L);
        }

        // 记录下请求内容
        logger.info("-->request请求URL : " + request.getRequestURL().toString());
        log.setRequestPath(request.getRequestURL().toString());
        logger.info("-->request请求方法HTTP_METHOD : " + request.getMethod());
        log.setRequestMethod(request.getMethod());
        logger.info("-->request请求方法IP : " + getIP(request));
        log.setRequestIp(getIP(request));
        logger.info("-->request请求类方法CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        if(request.getMethod().equals(HttpMethod.PUT.name())){
            log.setMethod(signature.getMethod().getDeclaredAnnotation(PutMapping.class).name());
        }
        if(request.getMethod().equals(HttpMethod.GET.name())){
            log.setMethod(signature.getMethod().getDeclaredAnnotation(GetMapping.class).name());
        }
        if(request.getMethod().equals(HttpMethod.POST.name())){
            log.setMethod(signature.getMethod().getDeclaredAnnotation(PostMapping.class).name());
        }
        if(request.getMethod().equals(HttpMethod.DELETE.name())){
            log.setMethod(signature.getMethod().getDeclaredAnnotation(DeleteMapping.class).name());
        }
        if(request.getMethod().equals(HttpMethod.PATCH.name())){
            log.setMethod(signature.getMethod().getDeclaredAnnotation(PatchMapping.class).name());
        }
        logger.info("-->request请求类方法 : " + log.getMethod());
        //todo 打印请求参数
//        logger.info("-->request请求ARGS : " + JSONUtils.obj2json(joinPoint.getArgs()));
//        log.setRequestParams(JSONUtils.obj2json(joinPoint.getArgs()));

        if(joinPoint.getTarget().getClass().isAnnotationPresent(Api.class)){
            log.setModular(joinPoint.getTarget().getClass().getAnnotation(Api.class).value());
        }
        log.setCreateTime(new Date().getTime());

        sysLogService.save(log);

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("-->response请求响应结果RESULT: " + ret);
        logger.info("-->response请求响应时间= 【" + (System.currentTimeMillis() - startTime.get())+"】毫秒");
        logger.info("################################: 结束请求 :###################################");
    }

    /**
     * 获取请求IP
     * @param request
     * @return
     */
    private static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("Proxy-Client-IP");

        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("WL-Proxy-Client-IP");

        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getRemoteAddr();

        }

        return ip;
    }
}
