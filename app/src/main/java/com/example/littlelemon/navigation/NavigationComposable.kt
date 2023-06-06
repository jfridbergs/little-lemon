package com.example.littlelemon.navigation

import android.content.Context
import android.provider.ContactsContract
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.Home
import com.example.littlelemon.UserProfile
import com.example.littlelemon.Onboarding

@Composable
fun Navigation(navController: NavHostController){

    val context = LocalContext.current
    val sharedPreferences by lazy {
        context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    }
    val name = sharedPreferences.getString("first_name", "no name was stored")
    NavHost(
        navController = navController,
        startDestination = if(  name.equals("no name was stored")) Onboarding.route else Home.route
    ) {
        composable(Home.route) {
            Home(navController = navController)
        }
        composable(UserProfile.route) {
            UserProfile(navController = navController)
        }
        composable(Onboarding.route) {
            Onboarding(navController = navController)
        }


    }
}