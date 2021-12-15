package com.groupnine.tebaktajwid.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.groupnine.tebaktajwid.R
import com.groupnine.tebaktajwid.model.Material
import java.util.*
import kotlin.collections.ArrayList

class MaterialViewModel: ViewModel() {
    private val _listMaterial = MutableLiveData<ArrayList<Material>>()
    val listMaterial: LiveData<ArrayList<Material>> get() = _listMaterial

    @SuppressLint("Recycle")
    fun getListMaterialData(context: Context) {
        val list: ArrayList<Material> = ArrayList()

        val icon = context.resources.obtainTypedArray(R.array.material_icon)
        val title = context.resources.getStringArray(R.array.material_title)
        val description = context.resources.getStringArray(R.array.material_description)
        val content = context.resources.getStringArray(R.array.material_content)
        for (i in title.indices) {
            val material = Material(icon.getResourceId(i, -1), title[i],
                    description[i], content[i])
            list.add(material)
        }

        _listMaterial.value = list
    }

    @SuppressLint("Recycle")
    fun getListMaterialDataByName(context: Context, name: String) {
        val list: ArrayList<Material> = ArrayList()

        val icon = context.resources.obtainTypedArray(R.array.material_icon)
        val title = context.resources.getStringArray(R.array.material_title)
        val description = context.resources.getStringArray(R.array.material_description)
        val content = context.resources.getStringArray(R.array.material_content)
        for (i in title.indices) {
            val material = Material(icon.getResourceId(i, -1), title[i],
                    description[i], content[i])
            if (material.title?.toLowerCase(Locale.ROOT)!!.contains(name.toLowerCase(Locale.ROOT)))
                list.add(material)
        }

        _listMaterial.value = list
    }
}