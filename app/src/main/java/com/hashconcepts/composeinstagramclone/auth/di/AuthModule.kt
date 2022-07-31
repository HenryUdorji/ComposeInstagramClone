package com.hashconcepts.composeinstagramclone.auth.di

import com.hashconcepts.composeinstagramclone.auth.data.AuthRepositoryImpl
import com.hashconcepts.composeinstagramclone.auth.data.FirebaseAuthenticator
import com.hashconcepts.composeinstagramclone.auth.domain.AuthRepository
import com.hashconcepts.composeinstagramclone.auth.domain.Authenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @created 30/07/2022 - 3:18 AM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */

@InstallIn(SingletonComponent::class)
@Module
object AuthModule {

    @Singleton
    @Provides
    fun provideAuthenticator(): Authenticator {
        return FirebaseAuthenticator()
    }

    @Singleton
    @Provides
    fun provideAuthRepository(authenticator: Authenticator): AuthRepository {
        return AuthRepositoryImpl(authenticator)
    }
}