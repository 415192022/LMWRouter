package com.lmw.lmwrouter.router;

import android.content.Intent;
import android.os.Bundle;

import com.lmw.lmwrouter.MainActivity;
import com.lmw.lmwrouter.detail.ItemDetailActivity;
import com.lmw.lmwrouter.lib.LMWRouter;
import com.lmw.lmwrouter.lib.util.Utils;
import com.lmw.lmwrouter.login.LoginActivity;


/**
 * <p>
 * 界面跳转工具类
 * Activity需要被其他Activity调用，都在这里声明，用于限定界面跳转需要传递哪些参数
 */

public class LMWRouterUtils {


    public static LMWRouterUtils getInstance() {
        return LMWRouterUtils.Holder.mInstance;
    }


    /**--------公共 start-------*/


    /**
     * 跳转到样例界面
     */
    public void startSample() {
        LMWRouter.getInstance().startActivity(MainActivity.class);
    }


    /**
     * 跳转到登录
     */
    public void startLogin() {
        startLogin("");
    }

    public void startLogin(String event) {
        Bundle bundle = new Bundle();
        bundle.putString("Key.EVENT", event);
        LMWRouter.getInstance().startActivity(LoginActivity.class, bundle);
    }

    public void startDetail() {
        LMWRouter.getInstance().startActivity(ItemDetailActivity.class);
    }

    /**
     * 一次打开多个Activity
     *
     * @param intents
     */
    public void startActivities(Intent[] intents) {
        Bundle bundle = new Bundle();
        LMWRouter.getInstance().startActivities(intents, Utils.getApp(), bundle);
    }


    /*** --------众筹 end-------*/

    /*** --------消息 end-------*/
    private static class Holder {
        private static LMWRouterUtils mInstance = new LMWRouterUtils();
    }


}
