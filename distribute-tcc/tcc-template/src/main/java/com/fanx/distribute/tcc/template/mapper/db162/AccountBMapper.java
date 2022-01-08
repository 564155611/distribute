package com.fanx.distribute.tcc.template.mapper.db162;

import com.fanx.distribute.tcc.template.entity.AccountB;
import com.fanx.distribute.tcc.template.entity.AccountBExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface AccountBMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_b
     *
     * @mbg.generated Fri Jan 07 23:03:03 CST 2022
     */
    long countByExample(AccountBExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_b
     *
     * @mbg.generated Fri Jan 07 23:03:03 CST 2022
     */
    int deleteByExample(AccountBExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_b
     *
     * @mbg.generated Fri Jan 07 23:03:03 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_b
     *
     * @mbg.generated Fri Jan 07 23:03:03 CST 2022
     */
    int insert(AccountB record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_b
     *
     * @mbg.generated Fri Jan 07 23:03:03 CST 2022
     */
    int insertSelective(AccountB record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_b
     *
     * @mbg.generated Fri Jan 07 23:03:03 CST 2022
     */
    List<AccountB> selectByExample(AccountBExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_b
     *
     * @mbg.generated Fri Jan 07 23:03:03 CST 2022
     */
    AccountB selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_b
     *
     * @mbg.generated Fri Jan 07 23:03:03 CST 2022
     */
    int updateByExampleSelective(@Param("record") AccountB record, @Param("example") AccountBExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_b
     *
     * @mbg.generated Fri Jan 07 23:03:03 CST 2022
     */
    int updateByExample(@Param("record") AccountB record, @Param("example") AccountBExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_b
     *
     * @mbg.generated Fri Jan 07 23:03:03 CST 2022
     */
    int updateByPrimaryKeySelective(AccountB record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_b
     *
     * @mbg.generated Fri Jan 07 23:03:03 CST 2022
     */
    int updateByPrimaryKey(AccountB record);
}