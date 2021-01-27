package com.zhongshi.puppy.service.insurance.config;

import com.alibaba.nacos.api.exception.NacosException;

public interface Closeable {
    void shutdown() throws NacosException;
}
