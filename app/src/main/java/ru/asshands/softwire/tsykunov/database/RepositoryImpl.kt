package ru.asshands.softwire.tsykunov.database

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.asshands.softwire.tsykunov.models.Post

class RepositoryImpl (applicationContext: Context):Repository{
    private val devLifeDb: AppDatabase = AppDatabase.create(applicationContext)

    override suspend fun getAll(): List<Post> = withContext(Dispatchers.IO) {
        TODO()
        //devLifeDb.postDao. getAll().map { toPost(it) }
    }

    override suspend fun addNew(post: Post) = withContext(Dispatchers.IO) {

        devLifeDb.postDao.insert(toEntity(post))
    }
    override suspend fun clearAll() = withContext(Dispatchers.IO) {
        devLifeDb.postDao.clearAll()
    }

    override suspend fun deleteById(id: Long) = withContext(Dispatchers.IO) {
        devLifeDb.postDao.deleteById(id)
    }


    private fun toEntity(post: Post) = PostEntity(
        id = post.id,
        description = post.description
        )

/*    private fun toPost(entity: PostEntity)
    = Post(
        id = entity.id,
        description = entity.description,

    )*/

}