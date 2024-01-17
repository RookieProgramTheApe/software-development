package online.niehong._002.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 004 切面类
 *
 * @author NieHong
 * @date 2024/01/04
 */
public class _004_SampleAspect {

    /**
     * 前置通知方法
     *
     * @param jp jp
     */
    public void before(JoinPoint jp) {
        // 获取即将要执行的类名称
        String clzName = jp.getTarget().getClass().getName();
        // 获取即将要执行的方法名
        String method = jp.getSignature().getName();
        // 方法参数
        Object[] args = jp.getArgs();

        System.out.println("前置通知执行 [clzName]: " + clzName + ",  [method]: " + method + ", [args]: " + args[0]);
    }

    /**
     * 后置通知方法
     *
     * @param jp jp
     */
    public void after(JoinPoint jp) {
        // 获取即将要执行的类名称
        String clzName = jp.getTarget().getClass().getName();
        // 获取即将要执行的方法名
        String method = jp.getSignature().getName();
        // 方法参数
        Object[] args = jp.getArgs();

        System.out.println("后置通知执行 [clzName]: " + clzName + ",  [method]: " + method + ", [args]: " + args[0]);
    }

    /**
     * 返回通知
     *
     * @param jp  jp
     * @param ret ret
     */
    public void afterReturning(JoinPoint jp, Object ret) {
        // 获取即将要执行的类名称
        String clzName = jp.getTarget().getClass().getName();
        // 获取即将要执行的方法名
        String method = jp.getSignature().getName();
        // 方法参数
        Object[] args = jp.getArgs();
        System.out.println("返回通知 [clzName]: " + clzName + ",  [method]: " + method + ", [args]: " + args[0]);
        System.out.println("[返回内容]: " + ret);
    }

    /**
     * 异常通知
     *
     * @param jp jp
     */
    public void exception(JoinPoint jp, Throwable t) {
        // 获取即将要执行的类名称
        String clzName = jp.getTarget().getClass().getName();
        // 获取即将要执行的方法名
        String method = jp.getSignature().getName();
        // 方法参数
        Object[] args = jp.getArgs();
        System.out.println("异常通知 [clzName]: " + clzName + ",  [method]: " + method + ", [args]: " + args[0]);
        System.out.println("[异常信息]: " + t);
    }


    public void around(ProceedingJoinPoint pjp) {
        // 获取即将要执行的类名称
        String clzName = pjp.getTarget().getClass().getName();
        // 获取即将要执行的方法名
        String method = pjp.getSignature().getName();
        // 方法参数
        Object[] args = pjp.getArgs();

        System.out.println("环绕通知");

        System.out.println("前置通知执行 [clzName]: " + clzName + ",  [method]: " + method + ", [args]: " + args[0]);

        try {
            Object r = pjp.proceed();
            System.out.println("返回通知 [clzName]: " + clzName + ",  [method]: " + method + ", [args]: " + args[0]);
            System.out.println("[返回内容]: " + r);
        } catch (Throwable e) {
            System.out.println("异常通知 [clzName]: " + clzName + ",  [method]: " + method + ", [args]: " + args[0]);
            System.out.println("[异常信息]: " + e);
            throw new RuntimeException(e);
        } finally {
            System.out.println("后置通知执行 [clzName]: " + clzName + ",  [method]: " + method + ", [args]: " + args[0]);
        }
    }
}
