package com.rizkyhamdana.janitraapp.repository

import androidx.lifecycle.LiveData
import com.rizkyhamdana.janitraapp.database.*

class JanitraRepository(private val janitraDao: JanitraDao) {

    suspend fun insertAllProducts(products: List<Products>) = janitraDao.insertAllProducts(products)

    suspend fun insertFavorite(favorite: Favorite) = janitraDao.insertFavorite(favorite)

    suspend fun insertCheckout(checkout: Checkout) = janitraDao.insertCheckout(checkout)

    suspend fun insertOrders(orders: Orders) = janitraDao.insertOrders(orders)

    fun getAllProducts(): LiveData<List<Products>> = janitraDao.getAllProducts()

    fun getAllCheckoutt(): LiveData<List<Checkout>> = janitraDao.getAllCheckoutt()

    fun getAllFavorite(): LiveData<List<Favorite>> = janitraDao.getAllFavorite()

    fun getALlOrders(): LiveData<List<Orders>> = janitraDao.getALlOrders()

    fun getFavorite(id: Int): LiveData<Favorite> = janitraDao.getFavorite(id)

    fun getSearchProduct(query: String): LiveData<List<Products>> = janitraDao.getSearchProduct(query)

    fun getProductByCategory(category: String): LiveData<List<Products>> = janitraDao.getProductByCategory(category)

    suspend fun deleteFavorite(favorite: Favorite) = janitraDao.deleteFavorite(favorite)

    suspend fun deleteCheckout(checkout: Checkout) = janitraDao.deleteCheckout(checkout)

    suspend fun clearOrders() = janitraDao.clearOrders()

    suspend fun clearCheckout() = janitraDao.clearCheckout()

    suspend fun updateCheckout(checkout: Checkout) = janitraDao.updateCheckout(checkout)

    fun getCheckout(id: Int) : LiveData<Checkout> = janitraDao.getCheckout(id)

}