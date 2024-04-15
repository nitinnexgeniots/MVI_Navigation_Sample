package com.nitin.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Main immediate dispatcher
 *
 * @constructor Create empty Main immediate dispatcher
 */
@Retention
@Qualifier
annotation class MainImmediateDispatcher // UI-related

/**
 * Io dispatcher
 *
 * @constructor Create empty Io dispatcher
 */
@Retention
@Qualifier
annotation class IoDispatcher // I/O-related

/**
 * Default dispatcher
 *
 * @constructor Create empty Default dispatcher
 */
@Retention
@Qualifier
annotation class DefaultDispatcher // CPU-related

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesDispatcherModule {

    @MainImmediateDispatcher
    @Singleton
    @Provides
    fun provideMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate

    @IoDispatcher
    @Singleton
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @DefaultDispatcher
    @Singleton
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
