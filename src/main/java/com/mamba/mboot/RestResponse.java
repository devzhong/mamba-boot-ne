package com.mamba.mboot;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mamba.mboot.boot.common.entity.Page;
import com.mamba.mboot.boot.common.exception.IError;
import com.mamba.mboot.boot.common.exception.NEError;

import java.util.HashMap;
import java.util.Map;

public class RestResponse<T> {
    private static final long serialVersionUID = -6602365878131231511L;
    public static final String DEFAULT_DATA_KEY = "data";
    public static final String DEFAULT_DATAS_KEY = "datas";
    private Status status;
    private String errorCode;
    private String errorMessage;
    private String extMessage;
    private Long pageIndex;
    private Long pageCount;
    private Long pageSize;
    private Long totalCount;
    private T data;
    private T datas;
    @JsonIgnore
    private Map<String, T> any;

    public RestResponse() {
        this.status = Status.SUCCEED;
    }

    public static RestResponse success() {
        RestResponse response = new RestResponse();
        return response;
    }

    public static RestResponse success(Object data) {
        return success((String)"data", (Object)data);
    }

    public static RestResponse success(String key, Object data) {
        return success(key, data, (Page)null);
    }

    public static RestResponse success(Object data, Page page) {
        return success("datas", data, page);
    }

    public static RestResponse success(String key, Object data, Page page) {
        RestResponse response = success();
        if (key.equals("data")){
            response.data = data;
        }else if(key.equals("datas")){
            response.datas = data;
        }else{
            response.put(key, data);
        }
        if(page != null) {
            response.page(page);
        }

        return response;
    }

    public static RestResponse error() {
        return error(NEError.SYSTEM_INTERNAL_ERROR);
    }

    public static RestResponse error(IError error) {
        RestResponse response = new RestResponse();
        response.errorCode = error.getErrorCode();
        response.errorMessage = error.getErrorMessage();
        response.status = Status.FAILED;
        return response;
    }

    public RestResponse put(T any) {
        if(this.any == null) {
            this.any = new HashMap();
        }

        this.any.put("data", any);
        return this;
    }

    public RestResponse put(String key, T data) {
        if(data == null) {
            return this;
        } else {
            if(this.any == null) {
                this.any = new HashMap();
            }

            this.any.put(key, data);
            return this;
        }
    }

    public RestResponse put(Map<String, T> any) {
        if(this.any == null) {
            this.any = new HashMap();
        }

        this.any.putAll(any);
        return this;
    }

    @JsonAnyGetter
    public Map<String, T> anyGetter() {
        return this.any;
    }

    @JsonAnySetter
    public void anySetter(String name, T value) {
        if(this.any == null) {
            this.any = new HashMap();
        }

        this.any.put(name, value);
    }

    private RestResponse page(Page page) {
        this.pageIndex = page.getPageIndex();
        this.pageCount = page.getPageCount();
        this.pageSize = page.getPageSize();
        this.totalCount = page.getTotalCount();
        return this;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExtMessage() {
        return this.extMessage;
    }

    public void setExtMessage(String extMessage) {
        this.extMessage = extMessage;
    }

    public Long getPageIndex() {
        return this.pageIndex;
    }

    public void setPageIndex(Long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Long getPageCount() {
        return this.pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public Long getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static enum Status {
        SUCCEED,
        FAILED;

        private Status() {
        }
    }
}
