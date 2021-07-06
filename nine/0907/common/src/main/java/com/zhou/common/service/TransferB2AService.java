package com.zhou.common.service;

import com.zhou.common.dto.TransferDTO;
import com.zhou.common.pojo.Transfer;

public interface TransferB2AService {
    void transaction(TransferDTO transferDTO);
}
