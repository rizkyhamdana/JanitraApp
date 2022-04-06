package com.rizkyhamdana.janitraapp.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rizkyhamdana.janitraapp.database.Favorite
import com.rizkyhamdana.janitraapp.database.JanitraDatabase
import com.rizkyhamdana.janitraapp.repository.JanitraRepository

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: JanitraRepository

    init {
        val janitraDao = JanitraDatabase.getDatabase(application).janitraDao()
        repository = JanitraRepository(janitraDao)
    }



    fun getAllFavorite(): LiveData<List<Favorite>> = repository.getAllFavorite()


}