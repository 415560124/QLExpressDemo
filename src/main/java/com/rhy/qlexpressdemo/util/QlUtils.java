package com.rhy.qlexpressdemo.util;

import org.springframework.stereotype.Component;

/**
 * @author: Herion Lemon
 * @date: 2021/4/18 15:50
 * @slogan: 如果你想攀登高峰，切莫把彩虹当梯子
 * @description:
 */
public class QlUtils {
    public static String upper(String abc) {
        return abc.toUpperCase();
    }
    public String upperInstantion(String abc) {
        return abc.toUpperCase();
    }
    public String test(String s){
        System.out.println(s);
        return s;
    }
}
