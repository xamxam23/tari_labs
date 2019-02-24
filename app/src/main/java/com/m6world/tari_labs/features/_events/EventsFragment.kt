package com.m6world.tari_labs.features._events

import android.os.Bundle
import android.view.View
import com.m6world.tari_labs.R
import com.m6world.tari_labs.api.models.AuthResponse
import com.m6world.tari_labs.api.models.EventCheckinsResponse
import com.m6world.tari_labs.commons.BaseFragment
import com.m6world.tari_labs.commons.JacksonMapper
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.events_fragment.*

class EventsFragment : BaseFragment() {
    var jsonMapper = JacksonMapper()
    override fun getLayout(): Int {
        return R.layout.events_fragment
    }

    var viewModel: EventsViewModel = EventsViewModel()
    var scanContent: String? = null
    var scanFormat: String? = null

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
            execute(viewModel.getEventCheckins(token.accessToken), object : Consumer<EventCheckinsResponse> {
                override fun accept(value: EventCheckinsResponse) {
                    var json = jsonMapper.toJson(value)
                    eventTextView.setText(json)
                    hideProgress()
                }
            })
    }

    fun setEventTextView(text: String?) {
        eventTextView.text = text
    }
}