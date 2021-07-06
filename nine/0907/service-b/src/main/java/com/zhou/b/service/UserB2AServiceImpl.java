package com.zhou.b.service;

import com.zhou.common.constant.Money;
import com.zhou.common.mapper.FreezeMapper;
import com.zhou.common.mapper.TransferMapper;
import com.zhou.common.mapper.UserMapper;
import com.zhou.common.pojo.Freeze;
import com.zhou.common.pojo.Transfer;
import com.zhou.common.pojo.User;
import com.zhou.common.service.FreezeA2BService;
import com.zhou.common.service.UserA2BService;
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
    @Autowired
    private FreezeMapper freezeMapper;
    @Autowired
    private UserA2BService userA2BService;
    @Autowired
    private FreezeA2BService freezeA2BService;
    @Autowired
    private TransferMapper transferMapper;

    @Transactional(rollbackFor = Exception.class)
    @HmilyTCC(confirmMethod = "settlementConfirm",cancelMethod = "settlementCancel")
    @Override
    public void settlement(Transfer transfer) {
        User user = userMapper.selectByUserId(transfer.getUser_id_from());
        String type_from = transfer.getType_from();
        String type_to = transfer.getType_to();
        //超过余额抛出异常
        if(Money.USD.equals(type_from)&&transfer.getMoney_from()>user.getUsd()){
            throw new HmilyRuntimeException("用户id："+user.getUser_id()+"，美元余额不足");
        }else if(Money.CNY.equals(type_from)&&transfer.getMoney_from()>user.getCny()){
            throw new HmilyRuntimeException("用户id："+user.getUser_id()+"，人名币余额不足");
        }
        //锁定金额
        //本地
        lock(transfer);
        //远端
        Transfer transfer2 = new Transfer();
        transfer2.setUser_id_from(transfer.getUser_id_to());
        transfer2.setTransfer_id(transfer.getTransfer_id());
        transfer2.setMoney_from(transfer.getMoney_to());
        transfer2.setMoney_from(transfer.getMoney_to());
        transfer2.setType_from(transfer.getType_to());
        userA2BService.lock(transfer2);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void settlementConfirm(Transfer transfer) {
        //取消锁定，添加余额
        //本地
        Freeze freeze = new Freeze();
        freeze.setUser_id(transfer.getUser_id_from());
        freeze.setTransfer_id(transfer.getTransfer_id());
        freezeMapper.deleteByUserId_TransferId(freeze);
        confirm(transfer);
        //远端
        Freeze freeze2 = new Freeze();
        freeze2.setUser_id(transfer.getUser_id_to());
        freeze2.setTransfer_id(transfer.getTransfer_id());
        freezeA2BService.delete(freeze2);
        Transfer transfer2 = new Transfer();
        transfer2.setUser_id_from(transfer.getUser_id_to());
        transfer2.setType_from(transfer.getType_to());
        transfer2.setMoney_to(transfer.getMoney_from());
        userA2BService.confirm(transfer2);
        //交易成功
        transfer.setStatus(Transfer.TransferStatus.SUCCESS);
        transferMapper.updateStatusByTransferId(transfer);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void settlementCancel(Transfer transfer) {
        //返回余额，取消锁定
        //本地
        Freeze freeze = new Freeze();
        freeze.setUser_id(transfer.getUser_id_from());
        freeze.setTransfer_id(transfer.getTransfer_id());
        freezeMapper.deleteByUserId_TransferId(freeze);
        cancel(transfer);
        //远端
        Freeze freeze2 = new Freeze();
        freeze2.setUser_id(transfer.getUser_id_to());
        freeze2.setTransfer_id(transfer.getTransfer_id());
        freezeA2BService.delete(freeze2);
        Transfer transfer2 = new Transfer();
        transfer2.setUser_id_from(transfer.getUser_id_to());
        transfer2.setType_from(transfer.getType_to());
        transfer2.setMoney_from(transfer.getMoney_to());
        userA2BService.cancel(transfer2);
        //交易失败
        transfer.setStatus(Transfer.TransferStatus.FAIL);
        transferMapper.updateStatusByTransferId(transfer);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void lock(Transfer transfer){
        User user = userMapper.selectByUserId(transfer.getUser_id_from());
        //扣减资金
        user.setUsd(user.getUsd()-transfer.getMoney_from());
        userMapper.updateByUserId(user);
        //记录冻结表
        Freeze freeze = new Freeze();
        freeze.setUser_id(transfer.getUser_id_from());
        freeze.setTransfer_id(transfer.getTransfer_id());
        freeze.setMoney(transfer.getMoney_from());
        freeze.setType(transfer.getType_from());
        freeze.setCreate_time(System.currentTimeMillis());
        freezeMapper.insert(freeze);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void confirm(Transfer transfer){
        //确认金额
        User user = userMapper.selectByUserId(transfer.getUser_id_from());
        String type_to = transfer.getType_to();
        if(Money.USD.equals(type_to)){
            user.setUsd(user.getUsd()+transfer.getMoney_to());
            userMapper.updateByUserId(user);
        }else if(Money.CNY.equals(type_to)){
            user.setCny(user.getCny()+transfer.getMoney_to());
            userMapper.updateByUserId(user);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cancel(Transfer transfer){
        //恢复金额
        User user = userMapper.selectByUserId(transfer.getUser_id_from());
        String type_from = transfer.getType_from();
        if(Money.USD.equals(type_from)){
            user.setUsd(user.getUsd()+transfer.getMoney_from());
            userMapper.updateByUserId(user);
        }else if(Money.CNY.equals(type_from)){
            user.setCny(user.getCny()+transfer.getMoney_from());
            userMapper.updateByUserId(user);
        }
    }

}
