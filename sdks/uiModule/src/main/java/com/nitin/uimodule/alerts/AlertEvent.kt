package com.nitin.uimodule.alerts

sealed class AlertEvent {
    object RefreshBootstrap : AlertEvent()
    data class UpdateShowAlertStatus(val showAlert: Boolean) : AlertEvent()
    data class NavigateToPage(val pagePath: String) : AlertEvent()
}