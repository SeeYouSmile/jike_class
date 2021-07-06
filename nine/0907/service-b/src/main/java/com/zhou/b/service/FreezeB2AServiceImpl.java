package com.zhou.b.service;

import com.zhou.common.mapper.FreezeMapper;
import com.zhou.common.pojo.Freeze;
import com.zhou.common.service.FreezeB2AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("freeze-b")
public class FreezeB2AServiceImpl implements FreezeB2AService {

    @Autowired
    private FreezeMapper freezeMapper;

    @Override
    public void delete(Freeze freeze) {
        freezeMapper.deleteByUserId_TransferId(freeze);
    }
}
