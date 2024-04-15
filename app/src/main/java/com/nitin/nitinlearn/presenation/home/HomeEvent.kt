package com.nitin.nitinlearn.presenation.home


sealed class HomeEvent {
    /**
     * Open web browser with details
     *
     * @property uri
     * @constructor Create empty Open web browser with details
     */
    data class OpenWebBrowserWithDetails(val uri: String) : HomeEvent()

    /**
     * Initialisation completed
     *
     * @property uri
     * @constructor Create empty Initialisation completed
     */
    data class InitialisationCompleted(val uri: String) : HomeEvent()

    /**
     * Switch tab
     *
     * @property pageId
     * @constructor Create empty Switch tab
     */
    data class SwitchTab(val index: Int) : HomeEvent()
}