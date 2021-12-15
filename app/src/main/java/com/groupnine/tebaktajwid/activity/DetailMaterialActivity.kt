package com.groupnine.tebaktajwid.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.groupnine.tebaktajwid.databinding.ActivityDetailMaterialBinding
import com.groupnine.tebaktajwid.model.Material

class DetailMaterialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMaterialBinding

    companion object {
        const val EXTRA_MATERIAL = "extra_material"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val material = intent.getParcelableExtra<Material>(EXTRA_MATERIAL)
        bind(material)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun bind(material: Material?) {
        with(binding) {
            Glide.with(this@DetailMaterialActivity).load(material?.icon).into(imgIcon)
            tvContent.text = material?.content
            tvTitle.text = material?.title
        }
    }
}