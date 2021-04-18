package com.rhy.qlexpressdemo.config;

import com.ql.util.express.ExpressRunner;
import com.rhy.qlexpressdemo.operator.JoinOperator;
import com.rhy.qlexpressdemo.util.QlUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Herion Lemon
 * @date: 2021/4/18 13:29
 * @slogan: 如果你想攀登高峰，切莫把彩虹当梯子
 * @description:
 */
@Configuration
public class QlExpressConfig {
    @Bean
    public ExpressRunner expressRunner() throws Exception {
        ExpressRunner runner = new ExpressRunner(true, true);
        runner.addOperator("join",joinOperator());
        runner.addFunction("joinMethod",joinOperator());
        runner.addFunctionOfClassMethod("MathAbs", Math.class.getName(), "abs", new String[] { "double" }, null);
        runner.addFunctionOfClassMethod("LongValueOf", Long.class.getName(), "valueOf", new String[]{"String"}, null);
        runner.addFunctionOfClassMethod("upper", QlUtils.class.getName(),"upper",new Class[]{String.class},null);
        runner.addFunctionOfServiceMethod("upperInstantion",qlUtils(),"upperInstantion",new Class[]{String.class},null);
        return runner;
    }
    @Bean
    public JoinOperator joinOperator(){
        return new JoinOperator();
    }
    @Bean
    public QlUtils qlUtils(){
        return new QlUtils();
    }
}
