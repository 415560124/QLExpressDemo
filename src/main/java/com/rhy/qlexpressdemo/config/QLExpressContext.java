package com.rhy.qlexpressdemo.config;

import com.ql.util.express.IExpressContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Herion Lemon
 * @date: 2021/4/18 17:03
 * @slogan: 如果你想攀登高峰，切莫把彩虹当梯子
 * @description:
 */
public class QLExpressContext extends HashMap<String, Object> implements IExpressContext<String, Object> {

    ApplicationContext applicationContext;

    public QLExpressContext(Map<String, Object> map, ApplicationContext applicationContext) {
        super(map);
        this.applicationContext = applicationContext;
    }

    /**
     * 抽象方法：根据名称从属性列表中提取属性值
     */
    @Override
    public Object get(Object name) {
        Object result = null;
        result = super.get(name);
        try {
            if (result == null && this.applicationContext != null
                    && this.applicationContext.containsBean((String) name)) {
                // 如果在Spring容器中包含bean，则返回String的Bean
                result = this.applicationContext.getBean((String) name);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    @Override
    public Object put(String name, Object object) {
        return super.put(name, object);
    }
}
