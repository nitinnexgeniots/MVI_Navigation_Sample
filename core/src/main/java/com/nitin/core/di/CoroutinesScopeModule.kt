package com.nitin.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Main immediate scope
 *
 * @constructor Create empty Main immediate scope
 */
@Retention
@Qualifier
annotation class MainImmediateScope // UI-related

/**
 * Io scope
 *
 * @constructor Create empty Io scope
 */
@Retention
@Qualifier
annotation class IoScope // I/O-related

/**
 * Default scope
 *
 * @constructor Create empty Default scope
 */
@Retention
@Qualifier
annotation class DefaultScope // CPU-related

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesScopeModule {

    @Singleton
    @Provides
    fun provideCoroutineExceptionHandler(): CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            Timber.e(throwable)
        }

    @MainImmediateScope
    @Singleton
    @Provides
    fun provideMainImmediateScope(
        @MainImmediateDispatcher mainImmediateDispatcher: CoroutineDispatcher,
        exceptionHandler: CoroutineExceptionHandler
    ): CoroutineScope = CoroutineScope(SupervisorJob() + mainImmediateDispatcher + exceptionHandler)

    @IoScope
    @Singleton
    @Provides
    fun provideIoScope(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        exceptionHandler: CoroutineExceptionHandler
    ): CoroutineScope = CoroutineScope(SupervisorJob() + ioDispatcher + exceptionHandler)

    @DefaultScope
    @Singleton
    @Provides
    fun provideDefaultScope(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher,
        exceptionHandler: CoroutineExceptionHandler
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher + exceptionHandler)
}
