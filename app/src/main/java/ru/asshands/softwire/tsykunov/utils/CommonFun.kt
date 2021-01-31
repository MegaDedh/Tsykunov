package ru.asshands.softwire.tsykunov.utils

import android.util.Log

fun <T> toLog(tag: String, collection:Collection<T>) {
    collection.forEach {
        Log.d(tag,it.toString())
    }
}