package com.rizkyhamdana.janitraapp.ui.category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rizkyhamdana.janitraapp.database.Products
import com.rizkyhamdana.janitraapp.databinding.ActivityCategoryBinding
import com.rizkyhamdana.janitraapp.ui.adapter.ListAdapter
import com.rizkyhamdana.janitraapp.ui.detail.DetailActivity

class CategoryActivity : AppCompatActivity() {

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var binding: ActivityCategoryBinding
    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        adapter = ListAdapter()

        val data = intent.getStringExtra(EXTRA_CATEGORY) as String
        title = "Kategori : $data"
        categoryViewModel.getProductByCategory(data).observe(this, {
            adapter.setProducts(it)
        })
        adapter.setOnItemClickCallback(object : ListAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Products) {
                val intent = Intent(this@CategoryActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, data)
                startActivity(intent)
            }

        })
        binding.rvList.adapter = adapter
    }

    companion object{
        const val EXTRA_CATEGORY = "extra_category"
    }
}