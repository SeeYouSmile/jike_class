package com.zhou.common.mapper;

import com.zhou.common.pojo.Transfer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface TransferMapper {
    @Insert("insert into transfer (transfer_id,user_id_from,user_id_to,money_from,money_to,type_from,type_to,status,create_time) value " +
            "(#{transfer_id},#{user_id_from},#{user_id_to},#{money_from},#{money_to},#{type_from},#{type_to},#{status},#{create_time})")
    int insert(Transfer transfer);

    @Update("update transfer set status=#{status} where transfer_id=#{transfer_id}")
    int updateStatusByTransferId(Transfer transfer);
}
