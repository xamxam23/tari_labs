package com.m6world.tari_labs.features._events

import android.os.Bundle
import android.view.View
import com.m6world.tari_labs.R
import com.m6world.tari_labs.api.models.EventCheckinsResponse
import com.m6world.tari_labs.features.BaseFragment
import com.m6world.tari_labs.commons.JacksonMapper
import com.m6world.tari_labs.features._abstract.IEventsViewModel
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.events_fragment.*
import javax.inject.Inject

class EventsFragment : BaseFragment() {
    @set:Inject var viewModel: IEventsViewModel? = null
    var jsonMapper = JacksonMapper()

    override fun getLayout(): Int {
        return R.layout.events_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        eventButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.eventButton -> getEvents()
        }
    }

    fun getEvents() {
        var token = getAuthResponse()
        if (token == null || token.accessToken == null) {
            toast("Error: token needed!")
            eventTextView.startAnimation(shake)
        } else
            execute(viewModel!!.getEventCheckins(token.accessToken), object : Consumer<EventCheckinsResponse> {
                override fun accept(value: EventCheckinsResponse) {
                    var json = jsonMapper.toJson(value)
                    eventTextView.setText(json)
                    hideProgress()
                }
            })
    }
}