package com.nitin.core.navigation
import androidx.navigation.NavGraphBuilder


interface NavigationFactory {
    /**
     * Create
     *
     * @param builder
     */
    fun create(builder: NavGraphBuilder)
}
