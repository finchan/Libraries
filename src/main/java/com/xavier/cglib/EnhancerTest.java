package com.xavier.cglib;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * Created by Xavier on 2017/7/13.
 */
public class EnhancerTest {
    public void testFixedValue()  {
        Enhancer enhancer = new Enhancer( );
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new FixedValue() {
            public Object loadObject() throws Exception {
                return "Hello cglib!";
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println(proxy.test(null));
        System.out.println(proxy.finalMethod());
    }

    public void testInvocationHandler( ) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return "Hello Cglib!";
                }else {
                    return proxy.invokeSuper(obj, args);
                }
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println(proxy.test(null));
        System.out.println(proxy.toString());
    }

    public void testCallBackFilter( ) throws Exception {
        Enhancer enhancer = new Enhancer();
        CallbackHelper callbackHelper = new CallbackHelper(SampleClass.class, new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return new FixedValue() {
                        public Object loadObject() throws Exception {
                            return "Hello cglib!";
                        }
                    };
                }else {
                    return NoOp.INSTANCE;
                }
            }
        };

        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallbackFilter(callbackHelper);
        enhancer.setCallbacks(callbackHelper.getCallbacks());
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println(proxy.test(null));
        System.out.println(proxy.toString());
        proxy.hashCode();
    }

    public static void main(String[] args) throws Exception{
        EnhancerTest test = new EnhancerTest();
        test.testFixedValue();
        test.testInvocationHandler();
        test.testCallBackFilter();
    }
}
