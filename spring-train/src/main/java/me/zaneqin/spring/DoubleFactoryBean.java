package me.zaneqin.spring;

import org.springframework.beans.factory.FactoryBean;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 14:05 2022/10/10
 * modifier:
 * modifyTime:
 */
public class DoubleFactoryBean implements FactoryBean<Double> {
    @Override
    public Double getObject() throws Exception {
        Double d = new Double("0.23");
        System.out.println("调用userFactoryBean的getObject方法生成bean:" + d);
        return d;
    }

    @Override
    public Class<?> getObjectType() {
        return Double.class;
    }
}
