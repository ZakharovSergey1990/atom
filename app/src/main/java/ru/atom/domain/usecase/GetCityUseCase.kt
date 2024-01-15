package ru.atom.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.atom.domain.model.Charger
import ru.atom.domain.model.Resource
import ru.atom.domain.model.ViewState
import ru.atom.domain.repository.ChargersRepository
import javax.inject.Inject

class GetCityUseCase @Inject constructor(
    private val chargersRepository: ChargersRepository
){
    fun execute(): Flow<ViewState<List<String>>> {
        return flow {
            emit(ViewState.Loading)
            val result: Resource<List<String>> =  chargersRepository.getCities()
            when(result){
                is Resource.Error -> emit(ViewState.Error(result.message))
                is Resource.Success -> emit(ViewState.Success(result.data))
            }
        }
    }
}