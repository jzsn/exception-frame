package com.exception.service;

import java.util.Date;

public class ExceptionConfigDTO {
    private Long exceptionId;
    private String sysType;
    private String providerId;
    private int exceptionCode;
    private String exceptionMessage;
    private int formatCode;
    private String formatMessage;
    private String regularFormula;
    private String resourceType;
    private String createdBy;
    private Date createdTime;
    private String updatedBy;
    private Date updatedTime;
    private int isDeleted;


    public Long getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(Long exceptionId) {
        this.exceptionId = exceptionId;
    }

    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public int getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(int exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public int getFormatCode() {
        return formatCode;
    }

    public void setFormatCode(int formatCode) {
        this.formatCode = formatCode;
    }

    public String getFormatMessage() {
        return formatMessage;
    }

    public void setFormatMessage(String formatMessage) {
        this.formatMessage = formatMessage;
    }

    public String getRegularFormula() {
        return regularFormula;
    }

    public void setRegularFormula(String regularFormula) {
        this.regularFormula = regularFormula;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
