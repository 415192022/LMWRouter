package com.lmw.lmwrouter.lib.interceptor;

import com.lmw.lmwrouter.lib.Docker;
import com.lmw.lmwrouter.lib.interceptor.BaseRouterInterceptor;

import java.util.List;

public class RouterInterceptorChain implements BaseRouterInterceptor.Chain {
    private final List<BaseRouterInterceptor> interceptors;
    private final Docker docker;
    private final int index;

    public RouterInterceptorChain(List<BaseRouterInterceptor> interceptors, int index, Docker docker) {
        this.interceptors = interceptors;
        this.index = index;
        this.docker = docker;
    }

    public boolean proceed(Docker docker) throws IndexOutOfBoundsException {
        if (index >= interceptors.size()) throw new IndexOutOfBoundsException();

        // Call the next interceptor in the chain.
        RouterInterceptorChain next = new RouterInterceptorChain(interceptors, index + 1, docker);
        BaseRouterInterceptor interceptor = interceptors.get(index);

        return interceptor.intercept(next);
    }

    @Override
    public Docker getDocker() {
        return docker;
    }


}