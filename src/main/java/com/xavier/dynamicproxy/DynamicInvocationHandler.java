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
public class DynamicInvocationHandler implements InvocationHandler {
    private static Logger logger = Logger.getLogger(DynamicInvocationHandler.class);

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        DI.info(logger, "Invoked method: {} " + method.getName());
        return 42;
    }
}
