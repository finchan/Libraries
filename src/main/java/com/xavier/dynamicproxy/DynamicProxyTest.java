package com.xavier.dynamicproxy;

import com.xavier.utilities.DI;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationHandler;
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
        Integer xx = (Integer)proxyInstance.put("hello", "world");
        DI.info(logger, "11111111111111 " +xx);

        Integer value = (Integer) proxyInstance.get("hello");
        DI.info(logger, "2222222222222 " +value);
        DI.info(logger, proxyInstance instanceof Map);

        Class<Map> proxyClazzByGetProxyClass =(Class<Map>) Proxy.getProxyClass(DynamicProxyTest.class.getClassLoader(), new Class[]{Map.class});

        InvocationHandler handler = Proxy.getInvocationHandler(proxyInstance);



//        Method[] methods = proxyObject.getMethods();
//        for(Method method : methods) {
//            DI.info(logger, method.getName());
//        }
    }
}
