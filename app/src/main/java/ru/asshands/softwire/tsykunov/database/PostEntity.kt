package ru.asshands.softwire.tsykunov.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = DbContract.ContractPost.TABLE_NAME,
    indices = [Index(DbContract.ContractPost.COLUMN_NAME_ID)]
)
data class PostEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbContract.ContractPost.COLUMN_NAME_ID)
    val id: Long = 0,

    @ColumnInfo(name = DbContract.ContractPost.COLUMN_NAME_POST_DESC)
    val description: String,
        )