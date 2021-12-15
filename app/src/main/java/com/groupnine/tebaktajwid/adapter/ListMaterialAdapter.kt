package com.groupnine.tebaktajwid.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.groupnine.tebaktajwid.activity.DetailMaterialActivity
import com.groupnine.tebaktajwid.databinding.ItemCardMaterialBinding
import com.groupnine.tebaktajwid.model.Material

class ListMaterialAdapter(private val context: Context, private val listMaterial: List<Material>) :
    RecyclerView.Adapter<ListMaterialAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemCardMaterialBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listMaterial[position])
    }

    override fun getItemCount(): Int {
        return listMaterial.size
    }

    inner class ListViewHolder(private val binding: ItemCardMaterialBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(material: Material) {
            with(binding) {
                Glide.with(context).load(material.icon).into(imgIcon)
                tvTitle.text = material.title
                tvDescription.text = material.description

                itemView.setOnClickListener {
                    val intent = Intent(context, DetailMaterialActivity::class.java)
                    intent.putExtra(DetailMaterialActivity.EXTRA_MATERIAL, material)
                    context.startActivity(intent)
                }
            }
        }
    }
}