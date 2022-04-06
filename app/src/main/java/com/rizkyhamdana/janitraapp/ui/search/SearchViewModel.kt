package com.rizkyhamdana.janitraapp.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rizkyhamdana.janitraapp.database.JanitraDatabase
import com.rizkyhamdana.janitraapp.database.Products
import com.rizkyhamdana.janitraapp.repository.JanitraRepository

class SearchViewModel(application: Application): AndroidViewModel(application) {
    private val repository: JanitraRepository

    init {
        val janitraDao = JanitraDatabase.getDatabase(application).janitraDao()
        repository = JanitraRepository(janitraDao)
    }

    fun getSearchProduct(query: String): LiveData<List<Products>> = repository.getSearchProduct(query)

}