package com.rhy.qlexpressdemo.operator;

import com.ql.util.express.Operator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author: Herion Lemon
 * @date: 2021/4/18 15:17
 * @slogan: 如果你想攀登高峰，切莫把彩虹当梯子
 * @description:
 */
public class JoinOperator extends Operator {
    @Override
    public Object executeInner(Object[] list) throws Exception {
        List res = null;
        //集合是否已填充标志
        boolean isList = false;
        for(int i=0;i<list.length;i++){
            //依次取出元素
            Object o = list[i];
            //没有填充过集合
            if(!isList){
                //元素是否为List
                if(o instanceof List){
                    //如果为List则沿用
                    res = (List) o;
                }else{
                    //不是List则新建，并且add到集合中
                    res = new ArrayList();
                    res.add(o);
                }
                isList = true;
            }else{
                //元素是否为List
                if(o instanceof List){
                    res.addAll((List) o);
                }else{
                    res.add(o);
                }
            }
        }
        return res;
    }
}
