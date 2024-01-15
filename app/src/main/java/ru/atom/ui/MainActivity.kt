package ru.atom.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.atom.ui.charger.ChargersPage
import ru.atom.ui.charger.ChargersViewModel
import ru.atom.ui.city.CityPage
import ru.atom.ui.city.CityPageViewModel
import ru.atom.ui.navigation.NavActions
import ru.atom.ui.navigation.Page
import ru.atom.ui.theme.AtomTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AtomTheme {
                val navController = rememberNavController()
                val navActions = NavActions(navController)
                NavHost(navController = navController, startDestination = Page.CITIES) {
                    composable(Page.CITIES) {
                        CityPage(vm = hiltViewModel<CityPageViewModel>().apply {
                            initNavActions(navActions)
                        })
                    }
                    composable(Page.CHARGERS) {
                        val city = it.arguments?.getString(Page.CITY)
                        ChargersPage(vm = hiltViewModel<ChargersViewModel>().apply {
                            init(city, navActions)
                        })
                    }
                }
            }
        }
    }
}