package com.m6world.tari_labs.commons

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.m6world.tari_labs.MainActivity
import com.m6world.tari_labs.R
import com.m6world.tari_labs.api.models.AuthResponse
import com.m6world.tari_labs.commons.event_bus.HideProgress
import com.m6world.tari_labs.commons.event_bus.ShowProgress
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus

abstract class BaseFragment : Fragment(), View.OnClickListener {
    var shake: Animation? = null
    var shake_long: Animation? = null

    var bounce: Animation? = null
    var bounce_long: Animation? = null

    fun setAuthResponse(authResponse: AuthResponse?) {
        (activity as MainActivity).authResponse = authResponse
    }

    fun getAuthResponse(): AuthResponse? {
        return (activity as MainActivity).authResponse
    }

    fun showProgress() {
        EventBus.getDefault().post(ShowProgress())
    }

    fun hideProgress() {
        EventBus.getDefault().post(HideProgress())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shake = AnimationUtils.loadAnimation(activity, R.anim.shake)
        shake_long = AnimationUtils.loadAnimation(activity, R.anim.shake_long)

        bounce = AnimationUtils.loadAnimation(activity, R.anim.bounce)
        bounce_long = AnimationUtils.loadAnimation(activity, R.anim.bounce_long)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), null)
    }

    abstract fun getLayout(): Int

    fun <T> schedule(o: Observable<T>): Observable<T> {
        return o.subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread());
    }

    @SuppressLint("CheckResult")
    fun <T> execute(observable: Observable<T>, AcceptConsumer: Consumer<T>) {
        showProgress()
        schedule(observable)
            .subscribe(AcceptConsumer, ThrowableConsumer());
    }

    fun ThrowableConsumer(): Consumer<Throwable> {
        return object : Consumer<Throwable> {
            override fun accept(t: Throwable?) {
                toastShort(t?.message)
                hideProgress()
            }
        }
    }

    fun toastLong(text: String?) {
        toast(text, Toast.LENGTH_LONG)
    }

    fun toastShort(text: String?) {
        toast(text, Toast.LENGTH_SHORT)
    }

    fun toast(text: String?) {
        toast(text, Toast.LENGTH_SHORT)
    }

    fun toast(text: String?, length: Int) {
        if (text != null && activity != null && !activity!!.isFinishing) {
            var toast = Toast.makeText(activity, text, length)
            // toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }
}