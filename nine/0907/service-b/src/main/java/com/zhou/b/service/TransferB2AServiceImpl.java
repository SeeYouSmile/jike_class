package com.zhou.b.service;

import com.zhou.common.dto.TransferDTO;
import com.zhou.common.mapper.TransferMapper;
import com.zhou.common.pojo.Transfer;
import com.zhou.common.service.CheckB2AService;
import com.zhou.common.service.TransferB2AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransferB2AServiceImpl implements TransferB2AService {

    @Autowired
    private TransferMapper transferMapper;
    @Autowired
    private CheckB2AService checkB2AService;

    @Override
    public void transfer(TransferDTO transferDTO) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(UUID.randomUUID().toString());
        transfer.setUserIdFrom(transferDTO.getUser_id_from());
        transfer.setUserIdTo(transferDTO.getUser_id_to());
        transfer.setMoney(transferDTO.getMoney());
        transfer.setType(transferDTO.getType());
        transfer.setStatus(Transfer.TransferStatus.PROCESSING);
        transfer.setCreateTime(System.currentTimeMillis());
        int i=transferMapper.insert(transfer);
        if(i>0){
            checkB2AService.check(transfer);
        }
    }
}
