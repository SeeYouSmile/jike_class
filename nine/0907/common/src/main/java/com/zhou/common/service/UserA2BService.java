package com.zhou.common.service;

import com.zhou.common.pojo.Freeze;
import com.zhou.common.pojo.Transfer;
import org.dromara.hmily.annotation.Hmily;

public interface UserA2BService {
    @Hmily
    void settlement(Transfer transfer);
    void settlementConfirm(Transfer transfer);
    void settlementCancel(Transfer transfer);

    void lock(Transfer transfer);
    void confirm(Transfer transfer);
    void cancel(Transfer transfer);
}
