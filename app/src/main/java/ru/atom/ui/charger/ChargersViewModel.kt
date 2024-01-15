package ru.atom.ui.charger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.atom.domain.model.Charger
import ru.atom.domain.model.ViewState
import ru.atom.domain.usecase.GetChargersUseCase
import ru.atom.ui.navigation.NavActions
import javax.inject.Inject


@HiltViewModel
class ChargersViewModel @Inject constructor(
    private val getChargersUseCase: GetChargersUseCase
): ViewModel() {

    private val _state = MutableStateFlow<ViewState<List<Charger>>>(ViewState.Loading)
    val state: StateFlow<ViewState<List<Charger>>> = _state

    private var navActions: NavActions? = null

    fun init(city: String?, navActions: NavActions){

        this.navActions = navActions

        if(city.isNullOrEmpty()){
            _state.value = ViewState.Error("Navigation error")
            return
        }
        viewModelScope.launch {
            getChargersUseCase.execute(city).collect{
                _state.value = it
            }
        }
    }

    fun onBackButtonClicked(){
        navActions?.goBack()
    }
}