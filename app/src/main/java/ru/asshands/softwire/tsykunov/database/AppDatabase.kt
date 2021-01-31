package ru.asshands.softwire.tsykunov.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.asshands.softwire.tsykunov.database.dao.PostDao

@Database(entities = [PostEntity::class], version = 1)
//@TypeConverters(value = [TypeResponseConverter::class])

abstract class AppDatabase : RoomDatabase() {

    abstract val postDao: PostDao

    companion object {

        fun create(context: Context): AppDatabase =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DbContract.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()

    }
}