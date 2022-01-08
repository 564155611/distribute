package com.fanx.distribute.tcc.template;

import lombok.Data;

@Data
public class TccResult<T> {

    private String code;
    /** 响应数据 */
    private T data;
    /** 响应状态 */
    private Boolean status = true;
    /** 响应消息 */
    private String msg;

}
