package com.example.digikala.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RealmModule {

    @Singleton
    @Provides
    fun provideProductDb(@ApplicationContext context: Context): Realm {
        Realm.init(context)
        val config: RealmConfiguration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
        Realm.compactRealm(config)
        return Realm.getDefaultInstance()
    }
}