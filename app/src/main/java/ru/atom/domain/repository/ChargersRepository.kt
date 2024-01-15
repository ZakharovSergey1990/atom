package ru.atom.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.atom.domain.model.Charger
import ru.atom.domain.model.Resource

interface ChargersRepository {
    suspend fun getCities(): Resource<List<String>>
    suspend fun getChargers(city: String): Resource<List<Charger>>
}