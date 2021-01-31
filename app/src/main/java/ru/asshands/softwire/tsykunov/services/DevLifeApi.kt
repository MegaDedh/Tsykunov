package ru.asshands.softwire.tsykunov.services

import retrofit2.http.GET
import ru.asshands.softwire.tsykunov.models.Post
import ru.asshands.softwire.tsykunov.models.Result

interface DevLifeApi {

    @GET("random?json=true")
    suspend fun getRandomPost(): Post

    @GET("latest/0?json=true&pageSize=25")
    suspend fun getLatestPosts(): Result

    @GET("hot/0?json=true&pageSize=25")
    suspend fun getHotPosts(): Result

    @GET("top/0?json=true&pageSize=25")
    suspend fun getTopPosts(): Result

}