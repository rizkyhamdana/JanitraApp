package com.rizkyhamdana.janitraapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizkyhamdana.janitraapp.database.Orders
import com.rizkyhamdana.janitraapp.databinding.ListHistoryBinding

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ListViewHolder>() {

    private var listHistory = ArrayList<Orders>()

    @SuppressLint("NotifyDataSetChanged")
    fun setHistory(history: List<Orders>?) {
        if (history == null) return
        this.listHistory.clear()
        this.listHistory.addAll(history)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val favorites = listHistory[position]
        holder.bind(favorites)
    }

    override fun getItemCount(): Int = listHistory.size


    class ListViewHolder(private val binding: ListHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: Orders) {
            with(binding) {
                tvDate.text = history.date
                tvNama.text = history.name
                tvPhone.text = history.whatsapp
            }
        }
    }
}