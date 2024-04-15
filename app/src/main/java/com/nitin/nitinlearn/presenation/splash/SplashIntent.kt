package com.nitin.nitinlearn.presenation.splash


/**
 * Home intent
 * @author Anand
 *
 * @constructor Create empty Home intent
 */
sealed class SplashIntent {
    object GetBootstrap : SplashIntent()
    object RefreshBootstrap : SplashIntent()
    object GetPages : SplashIntent()
    object AppForceUpgrade : SplashIntent()
    data class GetCountryCode(val anonymousToken: String?= null) : SplashIntent()
}