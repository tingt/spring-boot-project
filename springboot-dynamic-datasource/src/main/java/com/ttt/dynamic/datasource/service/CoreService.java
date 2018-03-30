package com.ttt.dynamic.datasource.service;

import com.ttt.dynamic.datasource.util.SpringContextUtil;
import org.springframework.stereotype.Component;

/**
 * @author tutingting
 * @date 2018/3/28 下午4:38
 */
@Component
public class CoreService {
    public CoreService getService(){
        return SpringContextUtil.getBean(this.getClass());
    }

}
