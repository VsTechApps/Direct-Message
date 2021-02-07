package com.vs.direct.message

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.hbb20.CountryCodePicker

class MainActivity : AppCompatActivity() {

    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

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
}