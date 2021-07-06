package com.zhou.a.service;

import com.zhou.common.mapper.FreezeMapper;
import com.zhou.common.pojo.Freeze;
import com.zhou.common.service.FreezeA2BService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("freeze-a")
public class FreezeA2BServiceImpl implements FreezeA2BService {

    @Autowired
    private FreezeMapper freezeMapper;

    @Override
    public void delete(Freeze freeze) {
        freezeMapper.deleteByUserId_TransferId(freeze);
    }
}
