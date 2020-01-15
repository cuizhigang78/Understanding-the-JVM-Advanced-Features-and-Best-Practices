package chapter02.p2_2_2;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 使用CGLib直接操作字节码运行，生成大量的动态类
 *
 * VM args: -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 */
public class JavaMethodAreaOOM {
    public static void main(final String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, args);
                }
            });
            enhancer.create();
        }
    }
    // 运行结果
    //Caused by: java.lang.OutOfMemoryError: PermGen space
    //	at java.lang.ClassLoader.defineClass1(Native Method)
    //	at java.lang.ClassLoader.defineClassCond(ClassLoader.java:631)
    //	at java.lang.ClassLoader.defineClass(ClassLoader.java:615)
    //	... 8 more

    static class OOMObject {

    }
}
