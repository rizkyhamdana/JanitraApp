package com.rizkyhamdana.janitraapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rizkyhamdana.janitraapp.database.Products
import com.rizkyhamdana.janitraapp.databinding.ListItemBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var listProducts = ArrayList<Products>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Products)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setProducts(products: List<Products>?) {
        if (products == null) return
        this.listProducts.clear()
        this.listProducts.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val products = listProducts[position]
        holder.bind(products)
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listProducts[position])
        }
    }

    override fun getItemCount(): Int = listProducts.size


    class ListViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Products) {
            with(binding) {
                tvName.text = product.name
                ("Rp. " + product.price.toString()).also { tvPrice.text = it }
                Glide.with(itemView.context)
                    .load(product.image)
                    .into(imgProduct)
            }
        }
    }
}