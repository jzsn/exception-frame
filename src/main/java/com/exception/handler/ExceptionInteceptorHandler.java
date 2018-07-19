package com.exception.handler;


import com.enn.cmp.broker.exception.IaasException;
import com.enn.cmp.exception.ApplicationException;
import com.enn.cmp.vo.Result;
import com.exception.service.ExceptionConfigDTO;
import com.exception.service.ExceptionConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@RestControllerAdvice
public class ExceptionInteceptorHandler {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionInteceptorHandler.class);

    @Autowired
    private ExceptionConfigService exceptionConfigService;


    private static final String MSG_PREFIX = "exception.";
    private static final String DEFAULT_MSG = "服务器异常";

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public Result exceptionHandler(Throwable throwable){
        logger.error("", throwable);
        if(throwable instanceof IaasException){
            IaasException iaasException = (IaasException) throwable;
            ExceptionConfigDTO param = new ExceptionConfigDTO();
            param.setProviderId(iaasException.getProviderId());
            param.setExceptionCode(iaasException.getCode());
            List<ExceptionConfigDTO> exceptionConfigs = exceptionConfigService.queryException(param);
            //设置默认返回异常结果
            ExceptionConfigDTO resultException = new ExceptionConfigDTO();
            resultException.setFormatCode(Result.FAILURE);
            resultException.setFormatMessage(DEFAULT_MSG);
            boolean flag = false;
            if(exceptionConfigs.size() > 0 ){
                for (ExceptionConfigDTO ex: exceptionConfigs) {
                    Pattern pattern = Pattern.compile(ex.getRegularFormula());
                    //判断该异常是否存在
                    if(pattern.matcher(iaasException.getMessage()).matches()){
                        flag = true;
                        resultException = ex;
                    }
                }
                // 未保存该异常 进行保存
                if(!flag){
                    ExceptionConfigDTO saveException = new ExceptionConfigDTO();
                    saveException.setProviderId(iaasException.getProviderId());
                    saveException.setExceptionCode(iaasException.getCode());
                    saveException.setExceptionMessage(iaasException.getMessage());
                    saveException.setResourceType(iaasException.getResourceType()== null ? "" : iaasException.getResourceType());
                    saveException.setCreatedTime(new Date());
                    saveException.setCreatedBy("sys");
                    saveException.setUpdatedBy("");
                    saveException.setIsDeleted(0);
                    exceptionConfigService.insertException(saveException);
                }
            }else{
                ExceptionConfigDTO saveException = new ExceptionConfigDTO();
                saveException.setProviderId(iaasException.getProviderId());
                saveException.setExceptionCode(iaasException.getCode());
                saveException.setExceptionMessage(iaasException.getMessage());
                saveException.setResourceType(iaasException.getResourceType()== null ? "" : iaasException.getResourceType());
                saveException.setCreatedTime(new Date());
                saveException.setCreatedBy("sys");
                saveException.setUpdatedBy("");
                saveException.setIsDeleted(0);
                exceptionConfigService.insertException(saveException);
            }
            logger.error("",throwable);
            return  Result.fail(resultException.getFormatCode(),resultException.getFormatMessage());
        }else {
            ApplicationException exception = (ApplicationException)throwable;
            logger.error("", throwable);
            return Result.fail(exception);
        }
    }
}
