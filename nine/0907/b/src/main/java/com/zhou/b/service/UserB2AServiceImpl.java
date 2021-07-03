package com.zhou.b.service;

import com.zhou.common.dto.TransferDTO;
import com.zhou.common.mapper.UserMapper;
import com.zhou.common.pojo.User;
import com.zhou.common.service.UserB2AService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.dromara.hmily.common.exception.HmilyRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService-b")
public class UserB2AServiceImpl implements UserB2AService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    @HmilyTCC(confirmMethod = "confirm",cancelMethod = "cancel")
    @Override
    public boolean transfer(TransferDTO transferDTO) {
        User user = userMapper.selectByUserId(transferDTO.getUser_id_from());
        String type = transferDTO.getType();
        //超过余额返回失败
        if(TransferDTO.Transfer.USD.equals(type)&&transferDTO.getMoney()>user.getUsd()){
            throw new HmilyRuntimeException("美元余额不足");
        }else if(TransferDTO.Transfer.CNY.equals(type)&&transferDTO.getMoney()>user.getCny()){
            throw new HmilyRuntimeException("人民币余额不足");
        }
        //锁定金额
        if(TransferDTO.Transfer.USD.equals(type)){
            user.setUsd(user.getUsd()-transferDTO.getMoney());
            user.setFreeze_usd(transferDTO.getMoney());
            return userMapper.updateByUserId(user)>0;
        }else if(TransferDTO.Transfer.CNY.equals(type)){
            user.setCny(user.getCny()-transferDTO.getMoney());
            user.setFreeze_cny(transferDTO.getMoney());
            return userMapper.updateByUserId(user)>0;
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean confirm(TransferDTO transferDTO) {
        User user = userMapper.selectByUserId(transferDTO.getUser_id_from());
        String type = transferDTO.getType();
        //取消锁定，添加余额
        if(TransferDTO.Transfer.USD.equals(type)){
            user.setFreeze_usd(0f);
            user.setCny(user.getCny()+transferDTO.getMoney()*7);
            return userMapper.updateByUserId(user)>0;
        }else if(TransferDTO.Transfer.CNY.equals(type)){
            user.setFreeze_cny(0f);
            user.setUsd(user.getUsd()+transferDTO.getMoney()/7);
            return userMapper.updateByUserId(user)>0;
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean cancel(TransferDTO transferDTO) {
        User user = userMapper.selectByUserId(transferDTO.getUser_id_from());
        String type = transferDTO.getType();
        //返回余额，取消锁定
        if(TransferDTO.Transfer.USD.equals(type)){
            user.setUsd(user.getUsd()+user.getFreeze_usd());
            user.setFreeze_usd(0f);
            return userMapper.updateByUserId(user)>0;
        }else if(TransferDTO.Transfer.CNY.equals(type)){
            user.setCny(user.getCny()+user.getFreeze_cny());
            user.setFreeze_cny(0f);
            return userMapper.updateByUserId(user)>0;
        }
        return false;
    }
}
