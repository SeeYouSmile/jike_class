package com.zhou.common.service;

import com.zhou.common.dto.TransferDTO;
import org.dromara.hmily.annotation.Hmily;

public interface UserA2BService {
    @Hmily
    boolean transfer(TransferDTO transferDTO);
    @Hmily
    boolean confirm(TransferDTO transferDTO);
    @Hmily
    boolean cancel(TransferDTO transferDTO);
}
