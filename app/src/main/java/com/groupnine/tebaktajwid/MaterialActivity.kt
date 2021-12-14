package com.groupnine.tebaktajwid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.groupnine.tebaktajwid.databinding.ActivityMaterialBinding

class MaterialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMaterialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}