package com.rhy.qlexpressdemo;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.rhy.qlexpressdemo.config.QLExpressContext;
import com.rhy.qlexpressdemo.config.QLExpressContextFactory;
import com.rhy.qlexpressdemo.entity.TestObject;
import com.rhy.qlexpressdemo.util.QlUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class QlExpressDemoApplicationTests {
    @Autowired
    ExpressRunner expressRunner;
    /**
     * 简单语句
     */
    @Test
    void easyDemo() throws Exception {
        DefaultContext<String,Object> context = new DefaultContext<>();
        context.put("a",1);
        context.put("b",2);
        context.put("c",3);
        //第一个
        String express = "a+b*c";
        Object execute = expressRunner.execute(express, context, null, true, true);
        System.out.println(execute);
        System.out.println("--------------------------------1----------------------------------------");
        //第二个 三元运算符
        express = "a>b?a:c";
        execute = expressRunner.execute(express, context, null, true, true);
        System.out.println(execute);
        System.out.println("--------------------------------2----------------------------------------");
        //第三个 ++ / --
        express = "a++";
//        express = "++a"; //不支持前++
        execute = expressRunner.execute(express, context, null, true, true);
        System.out.println(execute);
        express = "b--";
        execute = expressRunner.execute(express, context, null, true, true);
        System.out.println(execute);
        System.out.println("--------------------------------3----------------------------------------");
        //循环
        /** java代码 **/
        //把java代码里面的类型去掉就可以直接用了
        int n = 10;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + i;
        }
//        return sum;
        /** java代码结束 **/
        express = "n = 10;\n" +
                "        sum = 0;\n" +
                "        for (i = 0; i < n; i++) {\n" +
                "            sum = sum + i;\n" +
                "        }\n" +
                "        return sum;";
        execute = expressRunner.execute(express, context, null, true, true);
        System.out.println(execute);
        System.out.println("--------------------------------4----------------------------------------");
//        return sum;
        /** java代码结束 **/
        context = new DefaultContext<>();
        context.put("n","aaa");
        express = "return n == null";
        execute = expressRunner.execute(express, context, null, true, true);
        System.out.println(execute);
        System.out.println("--------------------------------5----------------------------------------");
    }
    @Test
    void listAndMap() throws Exception {
        //普通list集合
        /** java代码 **/
        //把java代码里面的类型去掉就可以直接用了
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        /** java代码结束 **/
        DefaultContext<String,Object> context = new DefaultContext<>();
        String express = "list = new ArrayList();\n" +
                "        list.add(1);\n" +
                "        list.add(2);\n" +
                "        list.add(3);\n" +
                "        for (int i = 0; i < list.size(); i++) {\n" +
                "            System.out.println(list.get(i));\n" +
                "        }";
        Object execute = expressRunner.execute(express, context, null, true, true);
        System.out.println("--------------------------------1----------------------------------------");
        //数组
        /** java代码 **/
        //QL的数组写法类似于js 把这个{}转为[]即可
        int[] ints = {1,2,3};
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
        /** java代码结束 **/
        express = "ints = [1,2,3];\n" +
                "        for (i = 0; i < ints.length; i++) {\n" +
                "            System.out.println(ints[i]);\n" +
                "        }";
        execute = expressRunner.execute(express, context, null, true, true);
        System.out.println(execute);
        System.out.println("--------------------------------2----------------------------------------");
        //map操作
        Map map = new HashMap();
        map.put("d1","value1");
        map.put("d2","value2");
        Set set = map.keySet();
        Object[] objects = set.toArray();
        for(int i=0;i<objects.length;i++){
            Object key=objects[i];
            System.out.println(map.get(key));
        }
        express = "map = new HashMap();\n" +
                "        map.put(\"d1\",\"value1\");\n" +
                "        map.put(\"d2\",\"value2\");\n" +
                "        set = map.keySet();\n" +
                "        objects = set.toArray();\n" +
                "        for(int i=0;i<objects.length;i++){\n" +
                "            key=objects[i];\n" +
                "            System.out.println(map.get(key));\n" +
                "        }";
        expressRunner.execute(express, context, null, true, true);
        System.out.println("--------------------------------3----------------------------------------");
        context = new DefaultContext<>();
        context.put("map",map);
        express = "return map.get('d1')";
        execute = expressRunner.execute(express, context, null, true, true);
        System.out.println(execute);
        System.out.println("--------------------------------4----------------------------------------");
    }

    @Test
    void beanTest() throws Exception {
        //系统自动会import java.lang.*,import java.util.*;
        //在ql表达式里创建对象
        /** java代码 **/
        //把java代码里面的类型去掉就可以直接用了
//        TestObject testObject = new TestObject().setId(1);
//        return testObject.getId();
        /** java代码结束 **/
        DefaultContext<String,Object> context = new DefaultContext<>();
        String express = "import com.rhy.qlexpressdemo.entity.TestObject;" +
                "testObject = new TestObject().setId(1);\n" +
                "return testObject.getId();";
        Object execute = expressRunner.execute(express, context, null, true, true);
        System.out.println(execute);
        System.out.println("--------------------------------1----------------------------------------");
        //传入java对象
        /** java代码 **/
        //把java代码里面的类型去掉就可以直接用了
        TestObject testObject = new TestObject().setId(1);
        testObject.getId();
        /** java代码结束 **/
        context = new DefaultContext<>();
        context.put("testObject",testObject);
        express = "return testObject.getId();";
        execute = expressRunner.execute(express, context, null, true, true);
        System.out.println(execute);
        System.out.println("--------------------------------2----------------------------------------");
    }
    @Test
    void operatorTest() throws Exception {
        DefaultContext<String,Object> context = new DefaultContext<>();
//        String express = "1 join 2 join 3";
//        Object execute = expressRunner.execute(express, context, null, true, true);
//        System.out.println(execute);
//        System.out.println("--------------------------------1----------------------------------------");
        String express = "joinMethod(1,2,3)";
        Object execute = expressRunner.execute(express, context, null, true, true);
        System.out.println(execute);
        System.out.println("--------------------------------2----------------------------------------");
    }
    @Test
    void functionTest() throws Exception {
        DefaultContext<String,Object> context = new DefaultContext<>();
        String express = "upperInstantion('demo')";
        Object execute = expressRunner.execute(express, context, null, true, true);
        System.out.println(execute);
        System.out.println("--------------------------------1----------------------------------------");
        express = "MathAbs(9399)";
        execute = expressRunner.execute(express, context, null, true, true);
        System.out.println(execute);
        System.out.println("--------------------------------2----------------------------------------");
        express = "LongValueOf('111')";
        execute = expressRunner.execute(express, context, null, true, true);
        System.out.println(execute);
        System.out.println("--------------------------------3----------------------------------------");
    }
    @Test
    void otherTest() throws Exception {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        for (int i = 0; i < integers.size(); i++) {
            System.out.println(integers.get(i));
        }
        DefaultContext<String,Object> context = new DefaultContext<>();
        context.put("integers",integers);
        String express = "for (i = 0; i < integers.size(); i++) {\n" +
                "            System.out.println(integers.get(i));\n" +
                "        }";
        Object execute = expressRunner.execute(express, context, null, true, true);
        System.out.println(execute);
        System.out.println("--------------------------------1----------------------------------------");
        context = new DefaultContext<>();
        context.put("obj",new QlUtils());
        context.put("abc","aaa");
        express = "return obj.test(abc)";
        execute = expressRunner.execute(express, context, null, true, true);
        System.out.println(execute);
        System.out.println("--------------------------------2----------------------------------------");
    }
    @Autowired
    QLExpressContextFactory qlExpressContextFactory;
    @Test
    void contextTest() throws Exception {
        QLExpressContext context = qlExpressContextFactory.getContext();
        context.put("abc","aaa");
        String express = "return qlUtils.test(abc)";
        Object execute = expressRunner.execute(express, context, null, true, true);
        System.out.println(execute);
        System.out.println("--------------------------------1----------------------------------------");
    }
}
