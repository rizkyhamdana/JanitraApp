package com.rizkyhamdana.janitraapp.util

import com.rizkyhamdana.janitraapp.data.CategoryEntity
import com.rizkyhamdana.janitraapp.database.Products

object DataDummy {

    fun generateCategory(): List<CategoryEntity> {
        val category = ArrayList<CategoryEntity>()
        category.add(
            CategoryEntity(
                "Ayam",
                "file:///android_asset/category/ic_ayam.png"
            )
        )
        category.add(
            CategoryEntity(
                "Sapi",
                "file:///android_asset/category/ic_sapi.png"
            )
        )
        category.add(
            CategoryEntity(
                "Ikan",
                "file:///android_asset/category/ic_ikan.png"
            )
        )
        category.add(
            CategoryEntity(
                "Sayur",
                "file:///android_asset/category/ic_sayur.png"
            )
        )

        return category
    }
}