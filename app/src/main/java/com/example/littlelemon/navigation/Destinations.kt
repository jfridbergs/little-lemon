package com.example.littlelemon.navigation

interface Destinations {
    val route: String
}

object Home : Destinations {
    override val route = "home"
}

object UserProfile : Destinations {
    override val route = "profile"
}

object Onboarding : Destinations {
    override val route = "onboarding"
}