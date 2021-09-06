package com.jmm.brsap.fcmdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jmm.brsap.fcmdemo.R
import com.jmm.brsap.fcmdemo.databinding.ActivityVendorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VendorActivity : AppCompatActivity() {
    private lateinit var binding:ActivityVendorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVendorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            val bottomSheet = PublishOffer()
            bottomSheet.show(supportFragmentManager,bottomSheet.tag)
        }
    }
}