package com.zhou.common.mapper;

import com.zhou.common.pojo.Transfer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface TransferMapper {
    @Insert("insert into transfer (transfer_id,user_id_from,user_id_to,money,type,status,create_time) value " +
            "(#{transferId},#{userIdFrom},#{userIdTo},#{money},#{type},#{status},#{createTime})")
    int insert(Transfer transfer);

    @Update("update transfer set status=#{status} where transfer_id=${transferId}")
    int updateStatusByTransferId(Transfer transfer);
}
