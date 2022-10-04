package com.joker.springframework.aop.aspectj;

import com.joker.springframework.aop.Pointcut;
import com.joker.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * Spring AOP Advisor that can be used for any AspectJ pointcut expression.
 * <p>
 * Advisor 访问者：把切面 Pointcut、拦截方法 Advice 和具体的拦截表达式包装在一起。
 * 这样就可以在 xml 的配置中定义一个 pointcutAdvisor 切面拦截器了。
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/4
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    /**
     * 切面
     */
    private AspectJExpressionPointcut pointcut;

    /**
     * 具体的拦截方法
     */
    private Advice advice;

    /**
     * 表达式
     */
    private String expression;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

}
