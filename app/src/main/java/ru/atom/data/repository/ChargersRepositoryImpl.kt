package ru.atom.data.repository

import kotlinx.coroutines.CancellationException
import kotlinx.serialization.json.Json
import ru.atom.data.api.Api
import ru.atom.data.dto.CityDto
import ru.atom.domain.model.Charger
import ru.atom.domain.model.Resource
import ru.atom.domain.repository.ChargersRepository
import java.lang.Exception
import javax.inject.Inject

class ChargersRepositoryImpl @Inject constructor(
    private val api: Api
): ChargersRepository {

    private var cash: List<CityDto> = emptyList()

    override suspend fun getCities(): Resource<List<String>> {
        try {
            val apiResult: Resource<String> = api.getChargers()
            if(apiResult is Resource.Error) return Resource.Error(apiResult.message)
            val jsonString: String = (apiResult as Resource.Success).data
            val result = Json.decodeFromString<List<CityDto>>(jsonString)
            cash = result
            return Resource.Success(cash.map{ it.city }.distinct())
        }
        catch (e: Exception){
            if( e is CancellationException) throw e
            return Resource.Error(e.message)
        }
    }

    override suspend fun getChargers(city: String): Resource<List<Charger>> {
        val chargers = cash.filter { it.city == city }
            .map { it.toCharger() }
        return if (chargers.isEmpty()) Resource.Error("Can't find chargers in this city")
        else Resource.Success(chargers)
    }

    private fun CityDto.toCharger() = Charger(
        address = charger.address,
        name = charger.name,
        busy = charger.busy
    )

}