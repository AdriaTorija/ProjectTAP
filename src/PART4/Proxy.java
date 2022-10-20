package PART4;

import java.lang.reflect.*;


/**
 * Proxy implementation
 */

public class Proxy implements InvocationHandler {

    private Object target = null;

    /**
     * Methot in which we return a new ProxyInstance to the target
     * @param target
     * @return
     */
    public static Object newInstance(Object target){
        var targetClass = target.getClass();
        var interfaces = targetClass.getInterfaces();

        return java.lang.reflect.Proxy.newProxyInstance(targetClass.getClassLoader(),
                interfaces, new Proxy(target));
    }

    /**
     * Private Constructor
     * @param target
     */
    private Proxy(Object target) {
        this.target = target;
    }

    /**
     * Invoke implementation in which we specify that it has to print when target calls
     * its own methods
     * @param proxy
     * @param method
     * @param args
     * @return the invocation result
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        Object invocationResult = null;
        try
        {
            System.out.println("S'ha cridat a " + method.getName());
            invocationResult = method.invoke(this.target, args);
            return invocationResult;

        }
        catch(InvocationTargetException ite )
        {
            throw ite.getTargetException();
        }
        catch(Exception e)
        {
            System.err.println("Error 583 del nostre codi d'errors");
        }
        return null;
    }

}
