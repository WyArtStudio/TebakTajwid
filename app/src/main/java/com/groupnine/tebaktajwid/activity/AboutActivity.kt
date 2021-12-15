package com.groupnine.tebaktajwid.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.groupnine.tebaktajwid.R
import com.groupnine.tebaktajwid.databinding.ActivityAboutBinding
import com.groupnine.tebaktajwid.util.NotificationBarColor

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NotificationBarColor().setColor(this, R.color.green_dark)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}