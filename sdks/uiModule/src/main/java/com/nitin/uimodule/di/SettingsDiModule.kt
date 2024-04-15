package com.nitin.uimodule.di

import com.nitin.uimodule.settings.SettingsUiState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent



@Module
@InstallIn(SingletonComponent::class)
object SettingsDiModule {
    @Provides
    fun provideSettingsUiState(): SettingsUiState = SettingsUiState()
}