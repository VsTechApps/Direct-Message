package com.vs.direct.message

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.hbb20.CountryCodePicker
import com.mopub.common.MoPub
import com.mopub.common.SdkConfiguration
import com.mopub.common.SdkInitializationListener
import com.mopub.common.logging.MoPubLog
import com.mopub.mobileads.MoPubView


class MainActivity : AppCompatActivity() {

    private lateinit var moPubView: MoPubView
    private val adID = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moPubView = findViewById(R.id.mopub)
        moPubView.setAdUnitId(adID)
        val configBuilder = SdkConfiguration.Builder(adID)

        configBuilder.withLogLevel(MoPubLog.LogLevel.INFO)
        MoPub.initializeSdk(this, configBuilder.build(), initSdkListener())

        val sentBtn = findViewById<Button>(R.id.send)
        val countryCodePicker = findViewById<CountryCodePicker>(R.id.countryCodePicker)
        val number = findViewById<EditText>(R.id.number)
        val message = findViewById<EditText>(R.id.message)

        sentBtn.setOnClickListener {

            val countryCode = countryCodePicker.selectedCountryCode

            if (number.text.trim().length == 10) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data =
                    Uri.parse("https://api.whatsapp.com/send?phone=${countryCode + number.text}&text=${message.text}")
                startActivity(intent)
            } else {
                number.error = "Enter a valid mobile number"
            }
        }
    }

    private fun initSdkListener(): SdkInitializationListener {
        return SdkInitializationListener {
            moPubView.loadAd()
        }
    }
}