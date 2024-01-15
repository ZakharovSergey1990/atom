package ru.atom.ui.city

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.atom.domain.model.ViewState
import ru.atom.domain.usecase.GetCityUseCase
import ru.atom.ui.navigation.NavActions
import javax.inject.Inject

@HiltViewModel
class CityPageViewModel @Inject constructor(
    private val getCityUseCase: GetCityUseCase
): ViewModel() {

    private val _state = MutableStateFlow<ViewState<List<String>>>(ViewState.Loading)
    val state: StateFlow<ViewState<List<String>>> = _state

    private var navActions: NavActions? = null

    init{
        viewModelScope.launch {
            getCityUseCase.execute().collect{
                _state.value = it
            }
        }
    }

    fun onCityClicked(city: String){
        navActions?.goToChargers(city)
    }

    fun initNavActions(navActions: NavActions){
        this.navActions = navActions
    }
}