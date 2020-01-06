package org.zerock.b2.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * TimeMapper
 */
public interface TimeMapper {

    @Select("select now()")
    public String getTime();



    
}