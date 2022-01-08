package com.fanx.distribute.tcc.template;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

@Slf4j
public class TccTemplate {

    /**
     * 分布式事务模板
     *
     * @param tccCallBack 分布式事务执行回调
     * @param method      当前方法名(封装参数, 可方便捞取数据)
     */
    public static TccResult process(TccCallBack tccCallBack, String method) {
        return process(v -> tccCallBack.tryExecute(),
                tccCallBack::confirm,
                v -> tccCallBack.cancel(),
                method);
    }

    public static TccResult process(Consumer<Void> tryExecute,
                                    BooleanSupplier confirm,
                                    Consumer<RuntimeException> cancel,
                                    String method) {
        // 返回一个消息用于
        TccResult tccResult = new TccResult();
        String msg = "";
        try {
            // 执行主业务
            tryExecute.accept(null);
            // 进行确认执行结果,如果结果是false,则执行回滚操作
            boolean flag = confirm.getAsBoolean();
            if (flag) {
                tccResult.setStatus(true);
                msg = String.format("分布式事务{%s}执行成功", method);
                log.info(msg);
            } else {
                tccResult.setStatus(false);
                msg = String.format("分布式事务{%s}执行失败,进行回滚操作", method);
                log.warn(msg);
                cancel.accept(null);
            }
        } catch (BusinessException e) {
            // 主流程发生异常, 则直接执行回滚操作
            tccResult.setStatus(false);
            tccResult.setCode(e.getErrorCode());
            msg = e.getErrorMessage();
            log.warn(String.format("分布式事务{%s}执行发生异常,进行回滚操作", method), e);
            cancel.accept(e);
            throw e;
        } catch (Exception e) {
            // 主流程发生异常, 则直接执行回滚操作
            tccResult.setCode(BizErrorCodeEnum.UNSPECIFIED.getCode());
            tccResult.setStatus(false);
            msg = e.getMessage();
            log.warn(String.format("分布式事务{%s}执行发生异常,进行回滚操作", method), e);
            cancel.accept(new RuntimeException(e));
            throw e;
        } finally {
            // 返回结果Result
            tccResult.setMsg(msg);
        }
        //返回语句不能放到finally里边,否则catch中的异常将不会被抛出
        return tccResult;
    }


    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put(1, "");
        map.put(2, "");
        map.put(3, "");
        map.put(4, "");
        map.put(5, "");

        try {
            map.forEach((k,v)->{
                throw new RuntimeException("xxxx");
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("yyy");
        }finally {
            return;
        }
    }


}