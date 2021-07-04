package com.zhou.common.service;

import com.zhou.common.dto.TransferDTO;
import org.dromara.hmily.annotation.Hmily;

public interface UserB2AService {
    @Hmily
    boolean transfer(TransferDTO transferDTO);
    boolean confirm(TransferDTO transferDTO);
    boolean cancel(TransferDTO transferDTO);
}
