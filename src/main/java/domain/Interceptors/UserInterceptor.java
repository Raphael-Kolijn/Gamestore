package domain.Interceptors;

import javax.interceptor.*;

public class UserInterceptor {
    @AroundInvoke
    public Object methodInterceptor(InvocationContext ctx) throws Exception {
        System.out.println("*** Intercepting call to LibraryBean method: "
                + ctx.getMethod().getName() + ctx.getParameters());

        return ctx.proceed();
    }
}
