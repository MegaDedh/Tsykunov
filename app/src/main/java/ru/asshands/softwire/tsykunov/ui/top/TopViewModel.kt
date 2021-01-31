package ru.asshands.softwire.tsykunov.ui.top

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import ru.asshands.softwire.tsykunov.R
import ru.asshands.softwire.tsykunov.models.Post
import ru.asshands.softwire.tsykunov.services.RetrofitModule
import ru.asshands.softwire.tsykunov.utils.toLog
import java.io.IOException
import java.net.UnknownHostException

class TopViewModel : ViewModel() {

    private val mutableDataList = MutableLiveData<List<Post>>()
    val dataList: LiveData<List<Post>> get() = mutableDataList

    private val mutableErrorId = MutableLiveData<Int>()
    val errorId: LiveData<Int> get() = mutableErrorId

    private var coroutineScope = CoroutineScope(Job() + Dispatchers.Main)
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(
            "TopViewModel",
            "Coroutine exception, scope active:${coroutineScope.isActive}",
            throwable
        )
        Log.e(
            "TopViewModel",
            throwable.toString()
        )

        val errorTextId = when (throwable) {
            is IOException, is HttpException, is UnknownHostException -> R.string.internet_connection_error_text
            is SerializationException -> R.string.parsing_error_text
            else -> R.string.unexpected_error_text
        }
        mutableErrorId.value = errorTextId
        coroutineScope = CoroutineScope(Job() + Dispatchers.Main)
    }

    fun loadData() {
        coroutineScope.launch(exceptionHandler) {
            mutableDataList.value = RetrofitModule.devLifeApi.getTopPosts().result
         }
    }

}