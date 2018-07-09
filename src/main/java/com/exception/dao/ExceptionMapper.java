package com.exception.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExceptionMapper {


    void insertException(ExceptionConfigPO ec);

    List<ExceptionConfigPO> queryException(ExceptionConfigPO ec);


}
