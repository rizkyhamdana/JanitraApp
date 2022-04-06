package com.rizkyhamdana.janitraapp.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.rizkyhamdana.janitraapp.database.Products
import com.rizkyhamdana.janitraapp.databinding.ActivitySearchBinding
import com.rizkyhamdana.janitraapp.ui.adapter.ListAdapter
import com.rizkyhamdana.janitraapp.ui.detail.DetailActivity

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        adapter = ListAdapter()

        val query = intent.getStringExtra(EXTRA_SEARCH) as String
        title = "Cari : $query"
        viewModel.getSearchProduct(query).observe(this, {
            if(it.isEmpty()){
                binding.animationNotfound.visibility = View.VISIBLE
            }else{
                binding.animationNotfound.visibility = View.GONE
                adapter.setProducts(it)
            }
        })

        binding.rvList.adapter = adapter
        adapter.setOnItemClickCallback(object : ListAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Products) {
                val intent = Intent(this@SearchActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, data)
                startActivity(intent)
            }

        })


    }

    companion object{
        const val EXTRA_SEARCH = "extra_search"
    }
}