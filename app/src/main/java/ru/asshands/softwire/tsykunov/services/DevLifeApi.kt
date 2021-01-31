package ru.asshands.softwire.tsykunov.services

import retrofit2.http.GET
import ru.asshands.softwire.tsykunov.models.Post

interface DevLifeApi {

    @GET("random?json=true")
    suspend fun getRandomPost(): Post

}