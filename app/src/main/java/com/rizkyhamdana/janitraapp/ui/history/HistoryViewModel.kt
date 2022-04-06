package com.rizkyhamdana.janitraapp.ui.history

import android.app.Application
import androidx.lifecycle.*
import com.rizkyhamdana.janitraapp.database.JanitraDatabase
import com.rizkyhamdana.janitraapp.database.Orders
import com.rizkyhamdana.janitraapp.repository.JanitraRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application): AndroidViewModel(application) {

    private val repository: JanitraRepository

    init {
        val janitraDao = JanitraDatabase.getDatabase(application).janitraDao()
        repository = JanitraRepository(janitraDao)
    }

    fun getALlOrders(): LiveData<List<Orders>> = repository.getALlOrders()

    fun clearOrders() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearOrders()
        }
    }
}