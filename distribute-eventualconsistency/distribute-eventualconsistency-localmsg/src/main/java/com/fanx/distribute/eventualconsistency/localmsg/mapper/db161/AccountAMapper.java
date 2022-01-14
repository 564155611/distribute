package com.fanx.distribute.eventualconsistency.localmsg.mapper.db161;

import com.fanx.distribute.eventualconsistency.localmsg.entity.AccountA;
import com.fanx.distribute.eventualconsistency.localmsg.entity.AccountAExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface AccountAMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_a
     *
     * @mbg.generated Sat Jan 08 15:14:14 CST 2022
     */
    long countByExample(AccountAExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_a
     *
     * @mbg.generated Sat Jan 08 15:14:14 CST 2022
     */
    int deleteByExample(AccountAExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_a
     *
     * @mbg.generated Sat Jan 08 15:14:14 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_a
     *
     * @mbg.generated Sat Jan 08 15:14:14 CST 2022
     */
    int insert(AccountA record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_a
     *
     * @mbg.generated Sat Jan 08 15:14:14 CST 2022
     */
    int insertSelective(AccountA record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_a
     *
     * @mbg.generated Sat Jan 08 15:14:14 CST 2022
     */
    List<AccountA> selectByExample(AccountAExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_a
     *
     * @mbg.generated Sat Jan 08 15:14:14 CST 2022
     */
    AccountA selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_a
     *
     * @mbg.generated Sat Jan 08 15:14:14 CST 2022
     */
    int updateByExampleSelective(@Param("record") AccountA record, @Param("example") AccountAExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_a
     *
     * @mbg.generated Sat Jan 08 15:14:14 CST 2022
     */
    int updateByExample(@Param("record") AccountA record, @Param("example") AccountAExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_a
     *
     * @mbg.generated Sat Jan 08 15:14:14 CST 2022
     */
    int updateByPrimaryKeySelective(AccountA record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_a
     *
     * @mbg.generated Sat Jan 08 15:14:14 CST 2022
     */
    int updateByPrimaryKey(AccountA record);
}