package me.zaneqin.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : ${TIME} ${DATE}
 * modifier:
 * modifyTime:
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DoubleFactoryBean.class);
        applicationContext.register(Main.class);
        applicationContext.refresh();

        System.out.println("获取到的bean为:" + applicationContext.getBean(Double.class));
        applicationContext.close();
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public LifeCycle lifeCycle() {
        return new LifeCycle();
    }
}