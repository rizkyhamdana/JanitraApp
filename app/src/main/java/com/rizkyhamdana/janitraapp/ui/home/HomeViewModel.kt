package com.rizkyhamdana.janitraapp.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rizkyhamdana.janitraapp.data.CategoryEntity
import com.rizkyhamdana.janitraapp.data.DataResponse
import com.rizkyhamdana.janitraapp.database.JanitraDatabase
import com.rizkyhamdana.janitraapp.database.Products
import com.rizkyhamdana.janitraapp.repository.JanitraRepository
import com.rizkyhamdana.janitraapp.util.DataDummy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: JanitraRepository
    private val listProducts = MutableLiveData<List<Products>>()

    init {
        val janitraDao = JanitraDatabase.getDatabase(application).janitraDao()
        repository = JanitraRepository(janitraDao)
    }

    fun getCategory(): List<CategoryEntity> = DataDummy.generateCategory()

    fun setData(){
        val products = ArrayList<Products>()
        val firebaseDb = FirebaseDatabase.getInstance(" https://janitra-app-43dcc-default-rtdb.asia-southeast1.firebasedatabase.app")
        firebaseDb.getReference("produk").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){
                    val product = i.getValue(DataResponse::class.java) as DataResponse
                    products.add(Products(
                        product.id,
                        product.name,
                        product.price,
                        product.desc,
                        product.category,
                        product.image
                    ))
                    listProducts.postValue(products)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Error: ", error.message)
            }
        })
    }

    fun getData(): LiveData<List<Products>> = listProducts

    fun insertAllProducts(products: List<Products>){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertAllProducts(products)
        }
    }


}