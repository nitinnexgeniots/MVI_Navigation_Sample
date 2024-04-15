package com.nitin.nitinlearn.di

import com.nitin.core.navigation.NavigationManager
import com.nitin.nitinlearn.navigation.NavigationManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    /**
     * Bind navigation manager
     *
     * @param impl
     * @return
     */
    @Binds
    @Singleton
    fun bindNavigationManager(impl: NavigationManagerImpl): NavigationManager
}
