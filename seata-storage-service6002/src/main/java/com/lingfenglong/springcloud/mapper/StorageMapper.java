package com.lingfenglong.springcloud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageMapper {
    // 减少库存
    void decrease(@Param("id") Long id, @Param("count") Integer count);
}
