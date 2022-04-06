package com.rizkyhamdana.janitraapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rizkyhamdana.janitraapp.database.Favorite
import com.rizkyhamdana.janitraapp.databinding.ListFavoriteBinding

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.ListViewHolder>() {

    private var listFavorite = ArrayList<Favorite>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Favorite)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFavorites(favorites: List<Favorite>?) {
        if (favorites == null) return
        this.listFavorite.clear()
        this.listFavorite.addAll(favorites)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val favorites = listFavorite[position]
        holder.bind(favorites)
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listFavorite[position])
        }
    }

    override fun getItemCount(): Int = listFavorite.size


    class ListViewHolder(private val binding: ListFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favorites: Favorite) {
            with(binding) {
                tvName.text = favorites.name
                ("Rp. " + favorites.price.toString()).also { tvPrice.text = it }
                Glide.with(itemView.context)
                    .load(favorites.image)
                    .into(imgProduct)
            }
        }
    }
}