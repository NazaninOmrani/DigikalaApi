package com.example.digikala.framework.datasource.cache.database

import androidx.room.RoomDatabase

abstract class ProductDataBase : RoomDatabase() {

    abstract fun productDao(): ProductDao

}