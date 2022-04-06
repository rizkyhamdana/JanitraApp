package com.rizkyhamdana.janitraapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rizkyhamdana.janitraapp.database.Checkout
import com.rizkyhamdana.janitraapp.databinding.ListCheckoutBinding

class CheckoutAdapter: RecyclerView.Adapter<CheckoutAdapter.ListViewHolder>() {

    private var listFavorite = ArrayList<Checkout>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Checkout)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setFavorites(checkouts: List<Checkout>?) {
        if (checkouts == null) return
        this.listFavorite.clear()
        this.listFavorite.addAll(checkouts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListCheckoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val favorites = listFavorite[position]
        holder.bind(favorites)
        holder.btnDelete.setOnClickListener {
            onItemClickCallback.onItemClicked(favorites)
        }

    }

    override fun getItemCount(): Int = listFavorite.size


    class ListViewHolder(private val binding: ListCheckoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val btnDelete = binding.deleteButton
        fun bind(checkout: Checkout) {
            with(binding) {
                tvName.text = checkout.name
                tvQtyValue.text = checkout.quantity.toString()
                tvSubtotal.text = checkout.total.toString()
                ("Rp. " + checkout.total.toString()).also { tvSubtotal.text = it }
                Glide.with(itemView.context)
                    .load(checkout.image)
                    .into(imgProduct)
            }
        }
    }
}