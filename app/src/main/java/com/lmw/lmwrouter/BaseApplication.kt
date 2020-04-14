package com.lmw.lmwrouter

import android.app.Application
import android.widget.Toast
import com.lmw.fastbus.lib.FastBus
import com.lmw.lmwrouter.lib.LMWRouter
import com.lmw.lmwrouter.lib.interceptor.BaseRouterInterceptor
import com.lmw.lmwrouter.lib.interceptor.RealJumpInterceptor
import com.lmw.lmwrouter.router.LoggedInterceptor
import io.reactivex.android.schedulers.AndroidSchedulers

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initLMWRouter()
        initFastBus()
    }

    private fun initLMWRouter() {
        val interceptors: ArrayList<BaseRouterInterceptor> = arrayListOf()
        interceptors.add(LoggedInterceptor())
        interceptors.add(RealJumpInterceptor())
        LMWRouter.getInstance().setInterceptors(this, interceptors)
    }
    private fun initFastBus(){
        FastBus.init(AndroidSchedulers.mainThread(), "app")
        Toast.makeText(this, "initFastBus", Toast.LENGTH_LONG).show()
    }
}