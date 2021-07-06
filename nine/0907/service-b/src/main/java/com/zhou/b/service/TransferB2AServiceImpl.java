package com.zhou.b.service;

import com.zhou.common.constant.Money;
import com.zhou.common.dto.TransferDTO;
import com.zhou.common.mapper.TransferMapper;
import com.zhou.common.pojo.Transfer;
import com.zhou.common.service.TransferB2AService;
import com.zhou.common.service.UserB2AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransferB2AServiceImpl implements TransferB2AService {

    @Autowired
    private TransferMapper transferMapper;
    @Autowired
    private UserB2AService userB2AService;

    @Override
    public void transaction(TransferDTO transferDTO) {
        Transfer transfer = new Transfer();
        transfer.setTransfer_id(UUID.randomUUID().toString());
        transfer.setUser_id_from(transferDTO.getUser_id_from());
        transfer.setUser_id_to(transferDTO.getUser_id_to());
        transfer.setMoney_from(transferDTO.getMoney());
        String type_from = transferDTO.getType_from().toUpperCase();
        String type_to = transferDTO.getType_to().toUpperCase();
        Float from = Money.moneyMap.get(type_from);
        Float to = Money.moneyMap.get(type_to);
        transfer.setMoney_to(transferDTO.getMoney()*from/to);
        transfer.setType_from(type_from);
        transfer.setType_to(type_to);
        transfer.setStatus(Transfer.TransferStatus.PROCESSING);
        transfer.setCreate_time(System.currentTimeMillis());
        int i=transferMapper.insert(transfer);
        if(i>0){
            userB2AService.settlement(transfer);
        }
    }
}
