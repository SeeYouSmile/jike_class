package com.zhou.common.service;

import com.zhou.common.pojo.Transfer;
import org.dromara.hmily.annotation.Hmily;

public interface CheckA2BService {
    @Hmily
    void check(Transfer transfer);
    void confirm(Transfer transfer);
    void cancel(Transfer transfer);
}
