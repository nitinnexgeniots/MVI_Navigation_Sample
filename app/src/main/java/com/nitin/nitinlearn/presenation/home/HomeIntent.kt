package com.nitin.nitinlearn.presenation.home


sealed class HomeIntent {
    object RefreshBootstrap : HomeIntent()
    data class UpdateShowAlertStatus(val showAlert: Boolean) : HomeIntent()
    data class NavigateToPage(val pagePath: String) : HomeIntent()
}