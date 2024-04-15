package com.nitin.core.navigation


sealed class NavigationDestination(
    open var route: String
) {
    object Splash : NavigationDestination("splash")
    object Home : NavigationDestination("home")
    object PopBackStack : NavigationDestination("POP_BACK_STACK")
    object Search : NavigationDestination("/search")
    object NavMenu : NavigationDestination("/navMenu")
    object NoInternet : NavigationDestination("/noInternet")
    object Delete : NavigationDestination("/delete")

}

object PageRoutes {
    const val Home = "/home"
}
