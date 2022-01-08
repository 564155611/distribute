package com.fanx.distribute.lock.oversell.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fanx.distribute.lock.oversell.entity.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 产品表  Mapper 接口
 * </p>
 *
 * @author fanx
 * @since 2021-12-08
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
