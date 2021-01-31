package ru.asshands.softwire.tsykunov.ui.random

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.asshands.softwire.tsykunov.database.RepositoryImpl

class RandomViewModelFactory(
    private val applicationContext: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        RandomViewModel::class.java ->
            RandomViewModel(RepositoryImpl(applicationContext))
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}