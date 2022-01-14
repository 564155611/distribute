package com.fanx.distribute.eventualconsistency.localmsg.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.id
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.status
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.amount
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    private BigDecimal amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.receive_user
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    private String receiveUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.receive_mobile
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    private String receiveMobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.create_time
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.create_user
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    private Integer createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.update_time
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.update_user
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    private Integer updateUser;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.id
     *
     * @return the value of t_order.id
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.id
     *
     * @param id the value for t_order.id
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.status
     *
     * @return the value of t_order.status
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.status
     *
     * @param status the value for t_order.status
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.amount
     *
     * @return the value of t_order.amount
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.amount
     *
     * @param amount the value for t_order.amount
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.receive_user
     *
     * @return the value of t_order.receive_user
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    public String getReceiveUser() {
        return receiveUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.receive_user
     *
     * @param receiveUser the value for t_order.receive_user
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser == null ? null : receiveUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.receive_mobile
     *
     * @return the value of t_order.receive_mobile
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    public String getReceiveMobile() {
        return receiveMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.receive_mobile
     *
     * @param receiveMobile the value for t_order.receive_mobile
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile == null ? null : receiveMobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.create_time
     *
     * @return the value of t_order.create_time
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.create_time
     *
     * @param createTime the value for t_order.create_time
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.create_user
     *
     * @return the value of t_order.create_user
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    public Integer getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.create_user
     *
     * @param createUser the value for t_order.create_user
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.update_time
     *
     * @return the value of t_order.update_time
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.update_time
     *
     * @param updateTime the value for t_order.update_time
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.update_user
     *
     * @return the value of t_order.update_user
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    public Integer getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.update_user
     *
     * @param updateUser the value for t_order.update_user
     *
     * @mbg.generated Sat Jan 08 15:12:24 CST 2022
     */
    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }
}