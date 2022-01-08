package com.fanx.distribute.tcc.template;

public interface TccCallBack {
    /**
     * 执行主要分布式业务操作
     */
    void tryExecute();

    /**
     * 确认分布式业务操作最终结果,
     * 如果返回true,则不执行cancel,返回false则执行cancel
     */
    boolean confirm();

    /**
     * 取消操作
     */
    void cancel();
}
