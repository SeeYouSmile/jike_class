package com.zhou.a.service;

import com.zhou.common.dto.TransferDTO;
import com.zhou.common.mapper.TransferMapper;
import com.zhou.common.pojo.Transfer;
import com.zhou.common.service.CheckA2BService;
import com.zhou.common.service.UserA2BService;
import com.zhou.common.service.UserB2AService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.dromara.hmily.common.exception.HmilyRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckA2BServiceImpl implements CheckA2BService {

    @Autowired
    private UserA2BService userA2BService;
    @Autowired
    private UserB2AService userB2AService;
    @Autowired
    private TransferMapper transferMapper;

    @Transactional(rollbackFor = Exception.class)
    @HmilyTCC(confirmMethod = "confirm",cancelMethod = "cancel")
    @Override
    public void check(Transfer transfer) {
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setUser_id_from(transfer.getUserIdFrom());
        transferDTO.setMoney(transfer.getMoney());
        transferDTO.setType(transfer.getType());
        if(!userA2BService.transfer(transferDTO)){
            throw new HmilyRuntimeException("a转账确认失败");
        }
        TransferDTO transferDTO2 = new TransferDTO();
        transferDTO2.setUser_id_from(transfer.getUserIdTo());
        if(Transfer.CurrentType.USD.equals(transfer.getType())){
            transferDTO2.setType(Transfer.CurrentType.CNY);
            transferDTO2.setMoney(transfer.getMoney()*7);
        }else{
            transferDTO2.setType(Transfer.CurrentType.USD);
            transferDTO2.setMoney(transfer.getMoney()/7);
        }
        if(!userB2AService.transfer(transferDTO2)){
            throw new HmilyRuntimeException("b转账确认失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void confirm(Transfer transfer) {
        transfer.setStatus(Transfer.TransferStatus.SUCCESS);
        transferMapper.updateStatusByTransferId(transfer);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cancel(Transfer transfer) {
        transfer.setStatus(Transfer.TransferStatus.FAIL);
        transferMapper.updateStatusByTransferId(transfer);
    }
}
