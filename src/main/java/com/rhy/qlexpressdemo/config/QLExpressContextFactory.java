package com.rhy.qlexpressdemo.config;

import com.ql.util.express.IExpressContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Herion Lemon
 * @date: 2021/4/18 17:03
 * @slogan: 如果你想攀登高峰，切莫把彩虹当梯子
 * @description:
 */
@Component
public class QLExpressContextFactory implements ApplicationContextAware {

    ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public QLExpressContext getContext(){
        return new QLExpressContext(new HashMap<String,Object>(),applicationContext);
    }
}
