package com.fanx.distribute.tcc.template;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    private String errorCode;
    private String errorMessage;
    public BusinessException(String errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
