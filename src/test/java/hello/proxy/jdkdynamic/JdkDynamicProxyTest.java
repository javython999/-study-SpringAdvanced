package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {
    @Test
    void dynamicA() {
        AInterface target = new AImpl();

        TimeInvokationHandler handler = new TimeInvokationHandler(target);

        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);

        proxy.call();

        log.info("target.getClass={}", target.getClass());
        log.info("proxy.getClass={}", proxy.getClass());
    }


    @Test
    void dynamicB() {
        BInterface target = new BImpl();

        TimeInvokationHandler handler = new TimeInvokationHandler(target);

        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);

        proxy.call();

        log.info("target.getClass={}", target.getClass());
        log.info("proxy.getClass={}", proxy.getClass());
    }
}
