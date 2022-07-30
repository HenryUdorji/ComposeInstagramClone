package com.hashconcepts.composeinstagramclone.common.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @created 29/07/2022 - 10:46 PM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("instagramClonePref", Context.MODE_PRIVATE)
    }
}