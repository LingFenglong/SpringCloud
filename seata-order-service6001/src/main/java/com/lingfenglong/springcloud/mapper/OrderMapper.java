package com.lingfenglong.springcloud.mapper;

import com.lingfenglong.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    // 创建订单
    Long create(Order order);

    // 修改订单状态
    void update(@Param("id") Long id, @Param("status") Integer status);
}
