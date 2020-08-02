package com.demo.cgb.pj.common.aspect;


import com.demo.cgb.pj.common.annotation.RequiredLog;
import com.demo.cgb.pj.common.util.IPUtils;
import com.demo.cgb.pj.sys.dao.SysLogDao;
import com.demo.cgb.pj.sys.entity.SysLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.Date;

@Slf4j
@Aspect//说明这个类是一个切面
public class SysLogAspect {

    @Autowired
    private SysLogDao sysLogDao;


    /**
     * @Pointcut 注解用于定义切入点
     */
    @Pointcut("@annotation(com.demo.cgb.pj.common.annotation.RequiredLog)")
    public void logPointCut(){}


    /**
     * @Around 描述的方法为环绕通知，用于功能增强
     * @param jp 连接点（封装了要执行的目标方法信息）
     * @return 目标方法的执行结果
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint jp)throws Throwable{
        try{
            long t1 = System.currentTimeMillis();
            log.info("start:" + t1);
            Object result = jp.proceed();//调用下一个切面或目标方法
            long t2 = System.currentTimeMillis();
            log.info("after:" + t2);
            saveObject(jp,(t2-t1));
            return result;

        }catch (Throwable e){
            log.error(e.getMessage());
            throw e;
        }
    }


    private void saveObject(ProceedingJoinPoint jp,long time)throws Exception{

        Class<?> targetCls = jp.getTarget().getClass();
        MethodSignature ms = (MethodSignature)jp.getSignature();
        String methodName = targetCls.getName() + "." + ms.getName();
        String params = getRequestParams(jp);
        String operation = getOperation(targetCls,ms);

        //3.将日志信息储存到数据库
        SysLog log = new SysLog();
        log.setIp(IPUtils.getIpAddr());
        log.setUsername("admin");
        log.setMethod(methodName);
        log.setParams(params);
        log.setOperation(operation);
        log.setTime(time);
        log.setCreatedTime(new Date());
        sysLogDao.insertObject(log);


    }

    private String getRequestParams(ProceedingJoinPoint jp)throws JsonProcessingException{
        Object[] args = jp.getArgs();
        String params = "";
        if(args.length>0){
            params = new ObjectMapper().writeValueAsString(args);
        }

        return params;
    }

    private String getOperation(Class<?> targetCls,MethodSignature ms)throws NoSuchMethodException{
        String operation = "";
        Method declaredMethod = targetCls.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
        RequiredLog requiredLog = declaredMethod.getDeclaredAnnotation(RequiredLog.class);
        if(requiredLog!=null){
            operation = requiredLog.value();
        }
        return operation;
    }





}
