package com.rizkyhamdana.janitraapp.ui.checkout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.rizkyhamdana.janitraapp.database.Checkout
import com.rizkyhamdana.janitraapp.database.JanitraDatabase
import com.rizkyhamdana.janitraapp.database.Orders
import com.rizkyhamdana.janitraapp.repository.JanitraRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckoutViewModel(application: Application): AndroidViewModel(application) {
    private val repository: JanitraRepository

    init {
        val janitraDao = JanitraDatabase.getDatabase(application).janitraDao()
        repository = JanitraRepository(janitraDao)
    }


    fun getAllCheckoutt(): LiveData<List<Checkout>> = repository.getAllCheckoutt()


    fun deleteCheckout(checkout: Checkout) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCheckout(checkout)
        }
    }


    fun insertOrders(orders: Orders){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertOrders(orders)
        }
    }

    fun clearCheckout() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearCheckout()
        }
    }

}