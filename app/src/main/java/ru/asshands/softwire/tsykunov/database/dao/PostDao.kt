package ru.asshands.softwire.tsykunov.database.dao

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Dao;
import ru.asshands.softwire.tsykunov.database.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM post ORDER BY _id ASC")
    suspend fun getAll(): List<PostEntity>

    @Insert()
    suspend fun insert(post: PostEntity)

    @Query("DELETE FROM post")
    suspend fun clearAll()

    @Query("DELETE FROM post WHERE _id == :id")
    suspend fun deleteById(id: Long)

}