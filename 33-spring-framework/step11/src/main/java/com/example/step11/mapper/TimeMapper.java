package com.example.step11.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TimeMapper {
    @Select("SELECT CURRENT_TIMESTAMP FROM DUAL")
    String getTime();

    String getTime2();

}
