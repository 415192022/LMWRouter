package com.lmw.lmwrouter.lib.interceptor;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

import com.lmw.lmwrouter.lib.Docker;

public class RealJumpInterceptor implements BaseRouterInterceptor {

    @Override
    public boolean intercept(Chain chain) throws IndexOutOfBoundsException {

        Docker docker = chain.getDocker();
        if (!(docker.getContext() instanceof Activity)) {
            docker.getIntent().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (docker.isShareTransition()) {
            docker.getContext().startActivity(docker.getIntent(), docker.getOptions());
        } else if (docker.getOptions() != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            docker.getIntent().putExtras(docker.getOptions());
            docker.getContext().startActivity(docker.getIntent());
        } else {
            docker.getContext().startActivity(docker.getIntent());
        }
        return true;
    }
}
