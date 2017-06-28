package com.xavier.dynamicproxy;

import com.xavier.utilities.DI;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * Created by Xavier on 2017/6/28.
 */
public class DynamicProxyTest {
    private static Logger logger = Logger.getLogger(DynamicProxyTest.class);
    public static void main(String[] args) {
        Map proxyInstance = (Map) Proxy.newProxyInstance(
                DynamicProxyTest.class.getClassLoader(),
                new Class[] {Map.class},
                new DynamicInvocationHandler());
        proxyInstance.put("hello", "world");
        int value = (Integer) proxyInstance.get("hello");
        DI.info(logger, value);

        Class<Map> proxyObject =(Class<Map>) Proxy.getProxyClass(DynamicProxyTest.class.getClassLoader(), new Class[]{Map.class});
        DI.info(logger, proxyInstance instanceof Map);

//        Method[] methods = proxyObject.getMethods();
//        for(Method method : methods) {
//            DI.info(logger, method.getName());
//        }
    }
}
