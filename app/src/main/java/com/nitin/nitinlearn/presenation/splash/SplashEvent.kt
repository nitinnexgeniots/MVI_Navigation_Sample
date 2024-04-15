package com.nitin.nitinlearn.presenation.splash


/**
 * Home event
 * @author Anand
 *
 * @constructor Create empty Home event
 */
sealed class SplashEvent {
    /**
     * Open web browser with details
     *
     * @property uri
     * @constructor Create empty Open web browser with details
     */
    data class OpenWebBrowserWithDetails(val uri: String) : SplashEvent()

    /**
     * Initialisation completed
     *
     * @property uri
     * @constructor Create empty Initialisation completed
     */
    data class InitialisationCompleted(val uri: String) : SplashEvent()

    /**
     * Switch tab
     *
     * @property pageId
     * @constructor Create empty Switch tab
     */
    data class SwitchTab(val pageId: String) : SplashEvent()
}