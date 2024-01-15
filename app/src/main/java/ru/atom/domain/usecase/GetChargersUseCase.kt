package ru.atom.domain.usecase

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.atom.domain.model.Charger
import ru.atom.domain.model.Resource
import ru.atom.domain.model.ViewState
import ru.atom.domain.repository.ChargersRepository
import javax.inject.Inject

class GetChargersUseCase @Inject constructor(
    private val chargersRepository: ChargersRepository
){
    fun execute(city: String): Flow<ViewState<List<Charger>>>{
        return flow {
           emit(ViewState.Loading)
           val result: Resource<List<Charger>> =  chargersRepository.getChargers(city)
           when(result){
               is Resource.Error -> emit(ViewState.Error(result.message))
               is Resource.Success -> emit(ViewState.Success(result.data.sortedBy { it.name }))
           }
        }.flowOn(Dispatchers.IO)
    }
}