package com.zhou.common.mapper;

import com.zhou.common.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Select("select id,user_id,cny,usd from user where user_id=#{user_id}")
    User selectByUserId(String userId);
    @Update("update user set cny=#{cny},usd=#{usd} where user_id=#{user_id}")
    int updateByUserId(User user);
}
