package com.rhy.qlexpressdemo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: Herion Lemon
 * @date: 2021/4/18 14:09
 * @slogan: 如果你想攀登高峰，切莫把彩虹当梯子
 * @description:
 */
@Data
@Accessors(chain = true)
public class TestObject {
    private Integer id;
}
