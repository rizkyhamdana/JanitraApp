package com.rizkyhamdana.janitraapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rizkyhamdana.janitraapp.data.CategoryEntity
import com.rizkyhamdana.janitraapp.databinding.ListCategoryBinding

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var listCategory = ArrayList<CategoryEntity>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: CategoryEntity)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCategories(categories: List<CategoryEntity>?) {
        if (categories == null) return
        this.listCategory.clear()
        this.listCategory.addAll(categories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ListCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val models = listCategory[position]
        holder.bind(models)
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listCategory[position])
        }
    }

    override fun getItemCount(): Int = listCategory.size


    class CategoryViewHolder(private val binding: ListCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: CategoryEntity) {
            with(binding) {
                tvNameCategory.text = category.name
                Glide.with(itemView.context)
                    .load(category.image)
                    .into(imgCategory)
            }
        }
    }
}