package com.exception.service.impl;

import com.exception.dao.ExceptionConfigPO;
import com.exception.dao.ExceptionMapper;
import com.exception.service.ExceptionConfigDTO;
import com.exception.service.ExceptionConfigService;
import com.exception.utils.BeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExceptionConfigServiceImpl implements ExceptionConfigService {

    private static final Logger log = LoggerFactory.getLogger(ExceptionConfigServiceImpl.class);

    @Autowired
    ExceptionMapper exceptionMapper;

    @Override
    public List<ExceptionConfigDTO> queryException(ExceptionConfigDTO ec) {
        List<ExceptionConfigDTO> dtos = new ArrayList<ExceptionConfigDTO>();
        try{
            ExceptionConfigPO  po = BeanMapper.map(ec,ExceptionConfigPO.class);
            dtos = BeanMapper.mapList(exceptionMapper.queryException(po),ExceptionConfigDTO.class);
        }catch (Exception e){
            log.error("查询异常列表失败------------------->{}",e);
        }
        return dtos;
    }

    @Override
    public void insertException(ExceptionConfigDTO ec) {
        try{
            ExceptionConfigPO  po = BeanMapper.map(ec,ExceptionConfigPO.class);
            exceptionMapper.insertException(po);
        }catch (Exception e){
            log.error("插入异常失败------------------->{}",e);
        }
    }
}
