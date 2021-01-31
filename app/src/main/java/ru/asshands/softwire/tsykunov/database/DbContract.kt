package ru.asshands.softwire.tsykunov.database

import android.provider.BaseColumns

object DbContract {

    const val DATABASE_NAME = "DevLife.db"

    object ContractPost {
        const val TABLE_NAME = "post"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_POST_DESC = "description"
    }
}