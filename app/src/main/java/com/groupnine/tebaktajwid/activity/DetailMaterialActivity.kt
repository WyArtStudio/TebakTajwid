package com.groupnine.tebaktajwid.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.github.barteksc.pdfviewer.util.FileUtils
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
        showPdfFromAssets(material?.content!!)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun bind(material: Material?) {
        with(binding) {
            Glide.with(this@DetailMaterialActivity).load(material?.icon).into(imgIcon)
            tvTitle.text = material?.title
        }
    }

    private fun showPdfFromAssets(pdfName: String) {
        binding.pdfView.fromAsset(pdfName)
            .password(null) // if password protected, then write password
            .defaultPage(0) // set the default page to open
            .onPageError { page, _ ->
                Toast.makeText(
                    this@DetailMaterialActivity,
                    "Error at page: $page", Toast.LENGTH_LONG
                ).show()
            }
            .load()
    }
}