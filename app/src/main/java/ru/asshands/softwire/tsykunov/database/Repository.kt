package ru.asshands.softwire.tsykunov.database

import ru.asshands.softwire.tsykunov.models.Post

interface Repository {

    suspend fun getAll(): List<Post>

    suspend fun addNew(post: Post)

    suspend fun clearAll()

    suspend fun deleteById(id: Long)

}