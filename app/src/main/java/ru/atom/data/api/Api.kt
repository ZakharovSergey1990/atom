package ru.atom.data.api

import kotlinx.coroutines.delay
import kotlinx.serialization.json.JsonObject
import ru.atom.domain.model.Resource
import javax.inject.Inject
import kotlin.random.Random

interface Api {
    suspend fun getChargers(): Resource<String>
}

class ApiImpl @Inject constructor(): Api{
    override suspend fun getChargers(): Resource<String> {
        delay(SERVER_DELAY)
        val random = Random.nextInt(0, 10)
        return if(random<5) Resource.Error("Network error")
        else Resource.Success(SERVER_JSON)
    }

    companion object{
        const val SERVER_JSON = "[{\"city\":\"Moscow\",\"charger\":{\"busy\":true,\"name\":\"Энергия Москвы\",\"address\":\"Измайловское ш., 4А\"}},{\"city\":\"Moscow\",\"charger\":{\"busy\":false,\"name\":\"Lipgart\",\"address\":\"2-я Бауманская ул., 5, стр. 5\"}},{\"city\":\"Saint Petersburg\",\"charger\":{\"busy\":true,\"name\":\"Станция зарядки электромобилей\",\"address\":\"Большой просп. Васильевского острова, 68\"}},{\"city\":\"Moscow\",\"charger\":{\"busy\":false,\"name\":\"Zevs\",\"address\":\"Мясницкая ул., 13, стр. 10\"}},{\"city\":\"Saint Petersburg\",\"charger\":{\"busy\":false,\"name\":\"Punkt E\",\"address\":\"Малая Монетная ул., 2Г\"}}]"
        const val SERVER_DELAY = 1000L
    }
}
