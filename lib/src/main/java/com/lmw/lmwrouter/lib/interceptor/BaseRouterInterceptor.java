package com.lmw.lmwrouter.lib.interceptor;


import com.lmw.lmwrouter.lib.Docker;

public interface BaseRouterInterceptor {
    boolean intercept(BaseRouterInterceptor.Chain chain) throws IndexOutOfBoundsException;

    interface Chain {
        boolean proceed(Docker docker) throws IndexOutOfBoundsException;

        Docker getDocker();
    }
}
