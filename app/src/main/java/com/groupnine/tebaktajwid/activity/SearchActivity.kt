package com.groupnine.tebaktajwid.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import com.groupnine.tebaktajwid.R
import com.groupnine.tebaktajwid.adapter.ListMaterialAdapter
import com.groupnine.tebaktajwid.databinding.ActivitySearchBinding
import com.groupnine.tebaktajwid.util.NotificationBarColor
import com.groupnine.tebaktajwid.viewmodel.MaterialViewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: MaterialViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NotificationBarColor().setColor(this, R.color.green_dark)

        viewModel = ViewModelProvider(this).get(MaterialViewModel::class.java)

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(name: Editable?) {
                if (name?.length!! > 0) {
                    viewModel.getListMaterialDataByName(this@SearchActivity, name.toString())
                }
            }
        })

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