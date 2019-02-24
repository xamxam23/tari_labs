package com.m6world.tari_labs

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.m6world.tari_labs.api.models.AuthResponse
import com.m6world.tari_labs.commons.event_bus.HideProgress
import com.m6world.tari_labs.commons.event_bus.ShowProgress
import com.m6world.tari_labs.commons.transformations.TransformFactory
import com.m6world.tari_labs.features.AccountsFragment
import com.m6world.tari_labs.features._events.EventsFragment
import com.m6world.tari_labs.features.HomeFragment
import com.m6world.tari_labs.features.TicketsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var authResponse: AuthResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
        setContentView(R.layout.activity_main)

        val adapter = MyPageAdapter(this, supportFragmentManager)
        viewPager.setAdapter(adapter)

        tabLayout.setupWithViewPager(viewPager)
        viewPager.setPageTransformer(false, TransformFactory.get(0))

        viewPager.offscreenPageLimit = 4

        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_baseline_home_24px)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_baseline_event_24px)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_baseline_receipt_24px)
        tabLayout.getTabAt(3)?.setIcon(R.drawable.ic_baseline_account_box_24px)
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    @Subscribe(threadMode = ThreadMode.MAIN) fun showProgress(showProgress: ShowProgress){
        progressBar.visibility = View.VISIBLE
    }

    @Subscribe(threadMode = ThreadMode.MAIN) fun showProgress(hideProgress: HideProgress){
        progressBar.visibility = View.INVISIBLE
    }
}

class MyPageAdapter(private val context: Context, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    // This determines the fragment for each tab
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return HomeFragment()
            1 -> return EventsFragment()
            2 -> return TicketsFragment()
            3 -> return AccountsFragment()
        }
        return throw Exception(context.getString(R.string.unknown_page))
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Home"
            1 -> return "Events"
            2 -> return "Tickets"
            3 -> return "Accounts"
        }
        return ""
    }
}