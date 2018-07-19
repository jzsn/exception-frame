package com.exception.service;

import com.exception.dao.ExceptionConfigPO;

import java.util.List;

public interface ExceptionConfigService {

    public List<ExceptionConfigDTO> queryException(ExceptionConfigDTO ec);

    public void insertException(ExceptionConfigDTO ec);
}
