package com.igor.authapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.igor.authapp2.data.AuthRepository
import com.igor.authapp2.ui.view.ForgotPasswordScreen
import com.igor.authapp2.ui.view.HomeScreen
import com.igor.authapp2.ui.view.LoginScreen
import com.igor.authapp2.ui.view.RegisterScreen
import com.igor.authapp2.viewmodel.AuthViewModel
import com.igor.authapp2.viewmodel.AuthViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = AuthRepository()
        val authViewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(
            AuthViewModel::class.java)

        setContent {
            val navController: NavHostController = rememberNavController()

            NavHost(navController = navController, startDestination = "login") {
                composable("login") { LoginScreen(authViewModel, navController) }
                composable("register") { RegisterScreen(authViewModel, navController) }
                composable("forgotPassword") { ForgotPasswordScreen(authViewModel, navController) }
                composable("home") { HomeScreen(authViewModel, navController) } // Enviando ViewModel para HomeScreen
            }
        }
    }
}