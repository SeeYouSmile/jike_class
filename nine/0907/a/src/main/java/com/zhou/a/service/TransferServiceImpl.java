package com.zhou.a.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhou.common.dto.TransferDTO;
import com.zhou.common.service.UserA2BService;
import com.zhou.common.service.UserB2AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService{

    @Autowired
    private UserA2BService userA2BService;
    @Reference
    private UserB2AService userB2AService;

    @Override
    public String a2b(TransferDTO transferDTO) {
        userA2BService.transfer(transferDTO);
        TransferDTO transferDTO2 = new TransferDTO();
        transferDTO2.setUser_id_from(transferDTO.getUser_id_to());
        if(TransferDTO.Transfer.USD.equals(transferDTO.getType())){
            transferDTO2.setType(TransferDTO.Transfer.CNY);
            transferDTO2.setMoney(transferDTO.getMoney()*7);
        }else{
            transferDTO2.setType(TransferDTO.Transfer.USD);
            transferDTO2.setMoney(transferDTO.getMoney()/7);
        }
        userB2AService.transfer(transferDTO);
        return "交易成功";
    }

}
