package com.xavier.dynamicproxy;


import com.xavier.utilities.DI;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DebugProxy implements InvocationHandler {
    private static Logger logger = Logger.getLogger(DebugProxy.class);
    private Object proxied;
    public static Object newInstance(Object proxied) {
        return Proxy.newProxyInstance(proxied.getClass().getClassLoader(),
                proxied.getClass().getInterfaces(),
                new DebugProxy(proxied));
    }

    private DebugProxy(Object obj) {
        this.proxied = obj;
    }

    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        Object result;
        try{
            System.out.println("before method " + m.getName());
            result = m.invoke(proxied, args);
        } catch(InvocationTargetException e) {
            throw e.getTargetException();
        } catch(Exception e) {
            throw new RuntimeException("unexpected invocation exception:"+
                    e.getMessage());
        } finally {
            System.out.println("after method " + m.getName());
        }
        return result;
    }

    public static void main(String[] args) throws BazException {
        Foo fooOriginal = new FooImpl( );
        Foo fooProxyInstance = (Foo) DebugProxy.newInstance(fooOriginal);
        //Interesting thing!!!
        DI.info(logger, fooOriginal.hashCode() + "\n" + fooProxyInstance.hashCode());
        fooProxyInstance.bar(null);
    }
}
