package com.lmw.lmwrouter.router;

import android.content.ComponentName;

import com.lmw.lmwrouter.authorization.PersistentAuthor;
import com.lmw.lmwrouter.detail.ItemDetailActivity;
import com.lmw.lmwrouter.lib.Docker;
import com.lmw.lmwrouter.lib.interceptor.BaseRouterInterceptor;
import com.lmw.lmwrouter.login.LoginActivity;

import java.util.ArrayList;

/**
 * <p>
 * 用户未登入拦截
 */

public class LoggedInterceptor implements BaseRouterInterceptor {
    //黑名单
    private static ArrayList<String> mBlackList = new ArrayList<>();

    static {
        mBlackList.add(ItemDetailActivity.class.getName());
    }

    @Override
    public boolean intercept(Chain chain) throws IndexOutOfBoundsException {

        Docker docker = chain.getDocker();

        //假如在黑名单中，并且未登录，则跳转到登录
        if (PersistentAuthor.getInstance().loadAuthor() == null && mBlackList.contains(docker.getIntent().getComponent()
                .getClassName())) {
            docker.getIntent().setComponent(new ComponentName(docker.getIntent().getComponent().getPackageName(),
                    LoginActivity.class.getName()));
        }

        return chain.proceed(docker);
    }
}
