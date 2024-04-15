package com.nitin.nitinlearn.di

import com.nitin.core.navigation.NavigationFactory
import com.nitin.nitinlearn.presenation.home.HomeNavigationFactory
import com.nitin.nitinlearn.presenation.home.HomeUiState
import com.nitin.nitinlearn.presenation.splash.SplashNavigationFactory
import com.nitin.nitinlearn.presenation.splash.SplashUiState
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object SplashViewModelModule {

    @Provides
    fun provideInitialSplashUiState(): SplashUiState =
        SplashUiState(true, Pair(false,false), false)



    /**
     * Home singleton module
     *
     * @constructor Create empty Splash singleton module
     */
    @Module
    @InstallIn(SingletonComponent::class)
    interface SplashSingletonModule {
        /**
         * Bind splash navigation factory
         *
         * @param factory
         * @return
         */
        @Singleton
        @Binds
        @IntoSet
        fun bindSplashNavigationFactory(factory: SplashNavigationFactory): NavigationFactory
    }

}
@Module
@InstallIn(SingletonComponent::class)
interface HomeSingletonModule {
    /**
     * Bind home navigation factory
     *
     * @param factory
     * @return
     */
    @Singleton
    @Binds
    @IntoSet
    fun bindHomeNavigationFactory(factory: HomeNavigationFactory): NavigationFactory
}

@Module
@InstallIn(ViewModelComponent::class)
object HomeViewModelModule {

    @Provides
    fun provideInitialHomeUiState(): HomeUiState =
        HomeUiState(isLoading = true, isError = false)



}