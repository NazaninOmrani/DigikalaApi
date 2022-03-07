package com.example.digikala.realm

import io.realm.Realm

abstract class ProductDataBase : Realm.Transaction {

    abstract fun productDao(): ProductDao

}