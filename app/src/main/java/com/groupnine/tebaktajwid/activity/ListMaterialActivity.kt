package com.groupnine.tebaktajwid.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.groupnine.tebaktajwid.R
import com.groupnine.tebaktajwid.adapter.ListMaterialAdapter
import com.groupnine.tebaktajwid.databinding.ActivityListMaterialBinding
import com.groupnine.tebaktajwid.util.NotificationBarColor
import com.groupnine.tebaktajwid.viewmodel.MaterialViewModel

class ListMaterialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListMaterialBinding
    private lateinit var viewModel: MaterialViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NotificationBarColor().setColor(this, R.color.green_dark)

        viewModel = ViewModelProvider(this).get(MaterialViewModel::class.java)
        viewModel.getListMaterialData(this)
        viewModel.listMaterial.observe(this) {
            if (it != null) {
                val adapter = ListMaterialAdapter(this, it)
                binding.rvMaterial.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}