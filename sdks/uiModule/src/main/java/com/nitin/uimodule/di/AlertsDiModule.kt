package com.nitin.uimodule.di

import com.nitin.uimodule.alerts.AlertUiState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent



@Module
@InstallIn(SingletonComponent::class)
object AlertsDiModule {
    @Provides
    fun provideAlertUiState(): AlertUiState = AlertUiState()
}