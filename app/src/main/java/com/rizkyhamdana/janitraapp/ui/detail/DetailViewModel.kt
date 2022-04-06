package com.rizkyhamdana.janitraapp.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.rizkyhamdana.janitraapp.database.Checkout
import com.rizkyhamdana.janitraapp.database.Favorite
import com.rizkyhamdana.janitraapp.database.JanitraDatabase
import com.rizkyhamdana.janitraapp.repository.JanitraRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application): AndroidViewModel(application) {

    private val repository: JanitraRepository

    init {
        val janitraDao = JanitraDatabase.getDatabase(application).janitraDao()
        repository = JanitraRepository(janitraDao)
    }

    fun getFavorite(id: Int): LiveData<Favorite> = repository.getFavorite(id)

    fun getCheckout(id: Int) : LiveData<Checkout> = repository.getCheckout(id)

    fun insertFavorite(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFavorite(favorite)
        }
    }

    fun deleteFavorite(favorite: Favorite){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavorite(favorite)
        }
    }

    fun insertCheckout(checkout: Checkout){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCheckout(checkout)

        }
    }


    fun updateCheckout(checkout: Checkout){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCheckout(checkout)
        }
    }

}