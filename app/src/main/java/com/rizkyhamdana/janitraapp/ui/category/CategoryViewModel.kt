package com.rizkyhamdana.janitraapp.ui.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rizkyhamdana.janitraapp.database.JanitraDatabase
import com.rizkyhamdana.janitraapp.database.Products
import com.rizkyhamdana.janitraapp.repository.JanitraRepository

class CategoryViewModel(application: Application): AndroidViewModel(application) {
    private val repository: JanitraRepository

    init {
        val janitraDao = JanitraDatabase.getDatabase(application).janitraDao()
        repository = JanitraRepository(janitraDao)
    }

    fun getProductByCategory(category: String): LiveData<List<Products>> = repository.getProductByCategory(category)

}