package com.joker.springframework.aop.framework;

import com.joker.springframework.aop.AdvisedSupport;

/**
 * <p>
 * 代理工厂：主要解决关于 JDK 和 Cglib 两种代理的选择问题，用来创建代理对象。
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/4
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }

        return new JdkDynamicAopProxy(advisedSupport);
    }

}
