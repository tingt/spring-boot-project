package com.ttt.dubbo.consumer.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.ttt.dubbo.consumer.api.UserApi;

/**
 * @author : tutingting
 * @date : 2018/3/31 下午3:09
 */
@Service(version = "1.0.0",group = "user-group")
public class UserProviderImpl implements UserApi {
    @Override
    public String getUserName(int id) {
        return "ttt";
    }
}
