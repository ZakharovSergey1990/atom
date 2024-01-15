package ru.atom.ui.navigation

import androidx.navigation.NavController
import ru.atom.ui.navigation.Page.CHARGERS


object Page{
    const val CITIES = "/cities"
    const val CITY = "city"
    const val CHARGERS = "/chargers/{$CITY}"
}

class NavActions(private val navController: NavController) {

    fun goToChargers(city: String){
        val route = CHARGERS.replace("{${Page.CITY}}", city)
        navController.navigate(route)
    }

    fun goBack(){
        navController.popBackStack()
    }
}