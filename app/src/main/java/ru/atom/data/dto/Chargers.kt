package ru.atom.data.dto

import kotlinx.serialization.Serializable


@Serializable
data class CityDto(
    val charger: ChargerDto,
    val city: String
)
@Serializable
data class ChargerDto(
    val address: String,
    val busy: Boolean,
    val name: String
)