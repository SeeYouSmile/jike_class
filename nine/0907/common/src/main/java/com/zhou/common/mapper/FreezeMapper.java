package com.zhou.common.mapper;

import com.zhou.common.pojo.Freeze;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

public interface FreezeMapper {
    @Insert("insert into freeze (user_id,transfer_id,money,type,create_time) value " +
            "(#{user_id},#{transfer_id},#{money},#{type},#{create_time})")
    int insert(Freeze freeze);

    @Delete("delete from freeze where user_id=#{user_id} and transfer_id=#{transfer_id}")
    int deleteByUserId_TransferId(Freeze freeze);
}
