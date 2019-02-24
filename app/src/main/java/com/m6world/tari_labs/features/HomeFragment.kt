package com.m6world.tari_labs.features

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.zxing.integration.android.IntentIntegrator
import com.m6world.tari_labs.CaptureActivityAnyOrientation
import com.m6world.tari_labs.MainActivity
import com.m6world.tari_labs.R
import com.m6world.tari_labs.api.models.AuthResponse
import com.m6world.tari_labs.commons.BaseFragment
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment() {
    override fun getLayout(): Int {
        return R.layout.home_fragment
    }

    var viewModel: HomeViewModel = HomeViewModel()

    var scanContent: String? = null
    var scanFormat: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        redeemButton.visibility = View.GONE
        authButton.setOnClickListener(this)
        scanButton.setOnClickListener(this)
        refreshButton.setOnClickListener(this)
        redeemButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.authButton -> doAuth()
            R.id.refreshButton -> doRefresh()
            R.id.redeemButton -> doRedeem()
            R.id.scanButton -> doScan()
        }
    }

    fun doAuth() {
        execute(viewModel.getAuthToken(), object : Consumer<AuthResponse> {
            override fun accept(value: AuthResponse) {
                setAuthResponse(value)
                authTextView.setText("access: " + value.accessToken + "\n\nrefresh: " + value.refreshToken)
                authTextView.startAnimation(bounce)
                hideProgress()
            }
        })
    }

    fun doRefresh() {
        var authResponse = getAuthResponse()
        if (authResponse == null) toastShort("Error: Auth needed!")
        else if (authResponse?.accessToken == null || authResponse?.refreshToken == null) toast("Error: null token")
        else {
            var accessToken = authResponse?.accessToken!!
            var refreshToken = authResponse?.refreshToken!!
            execute(viewModel.getAuthToken(accessToken, refreshToken),
                object : Consumer<AuthResponse> {
                    override fun accept(value: AuthResponse) {
                        authResponse = value
                        authTextView.setText("access: " + value.accessToken + "\n\nrefresh: " + value.refreshToken)
                        authTextView.startAnimation(bounce)
                        hideProgress()
                    }
                });
        }
    }

    fun doRedeem() {
    }

    fun doScan() {
        val scanIntegrator = IntentIntegrator.forSupportFragment(this)
        scanIntegrator.setPrompt("Scan")
        scanIntegrator.setBeepEnabled(true)

        //enable the following line if you want QR code
        //scanIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);

        scanIntegrator.captureActivity = CaptureActivityAnyOrientation::class.java
        scanIntegrator.setOrientationLocked(true)
        scanIntegrator.setBarcodeImageEnabled(true)
        scanIntegrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (scanningResult != null) {
            if (scanningResult.contents != null) {
                scanContent = scanningResult.contents.toString()
                scanFormat = scanningResult.formatName.toString()
                var text = scanContent + "   type:" + scanFormat;
                setScanText(text)
                toastShort(text)
            } else toast("Error retrieving scan info")
        } else {
            toastShort("Error: Nothing scanned");
        }
    }

    fun setScanText(text: String?) {
        scanTextView.text = text
    }
}