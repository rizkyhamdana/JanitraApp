package com.rizkyhamdana.janitraapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface JanitraDao {

    @Insert(entity = Products::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllProducts(products: List<Products>)

    @Insert(entity = Favorite::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(favorite: Favorite)

    @Insert(entity = Checkout::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCheckout(checkout: Checkout)

    @Insert(entity = Orders::class)
    suspend fun insertOrders(orders: Orders)

    @Query("SELECT * FROM tb_products")
    fun getAllProducts(): LiveData<List<Products>>

    @Query("SELECT * FROM tb_checkout")
    fun getAllCheckoutt(): LiveData<List<Checkout>>

    @Query("SELECT * FROM tb_favorite")
    fun getAllFavorite(): LiveData<List<Favorite>>

    @Query("SELECT * FROM tb_favorite WHERE id = :id")
    fun getFavorite(id: Int): LiveData<Favorite>

    @Query("SELECT * FROM tb_checkout WHERE id = :id")
    fun getCheckout(id: Int) : LiveData<Checkout>

    @Query("SELECT * FROM tb_orders")
    fun getALlOrders(): LiveData<List<Orders>>

    @Query("SELECT * FROM tb_products WHERE name LIKE '%'||:query||'%'")
    fun getSearchProduct(query: String): LiveData<List<Products>>

    @Query("SELECT * FROM tb_products WHERE category=:category")
    fun getProductByCategory(category: String):LiveData<List<Products>>

    @Delete(entity = Favorite::class)
    suspend fun deleteFavorite(favorite: Favorite)

    @Delete(entity = Checkout::class)
    suspend fun deleteCheckout(checkout: Checkout)

    @Query("DELETE FROM tb_orders")
    suspend fun clearOrders()

    @Query("DELETE FROM tb_checkout")
    suspend fun clearCheckout()

    @Update(entity = Checkout::class)
    suspend fun updateCheckout(checkout: Checkout)

}