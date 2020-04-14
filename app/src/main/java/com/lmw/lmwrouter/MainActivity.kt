package com.lmw.lmwrouter

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lmw.fastbus.lib.FastBus
import com.lmw.fastbus.lib.annotations.Receive
import com.lmw.fastbus.lib.contrace.FastBusBinder
import com.lmw.lmwrouter.authorization.PersistentAuthor
import com.lmw.lmwrouter.consts.Key
import com.lmw.lmwrouter.router.LMWRouterUtils
import com.lmw.lmwrouter.user.PersistentUser
import com.lmw.lmwrouter.user.cache.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mFastBusBinder: FastBusBinder? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFastBusBinder = FastBus.bind(this)

        tvDetail?.setOnClickListener {
            LMWRouterUtils.getInstance().startDetail()
        }

        tvExitLogin?.setOnClickListener {
            PersistentAuthor.getInstance().clear()
            PersistentAuthor.getInstance().clearSession()
            PersistentUser.getInstance().clear()
            PersistentUser.getInstance().clearSession()
            FastBus.post(Key.EVENT_LOGIN_SUCCESS, PersistentUser.getInstance().loadUser())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        FastBus.unBind(mFastBusBinder)
    }

    override fun onResume() {
        super.onResume()

    }

    @Receive(Key.EVENT_LOGIN_SUCCESS)
    fun onLoginSuccess(user: User?) {
        tvInfo?.text = "token: " + "" + PersistentAuthor.getInstance().loadAuthor().value + "\nuserId:" + user?.value
        Toast.makeText(this, "" + user, Toast.LENGTH_LONG).show()
    }
}