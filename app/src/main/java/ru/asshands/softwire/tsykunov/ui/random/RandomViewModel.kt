package ru.asshands.softwire.tsykunov.ui.random

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import ru.asshands.softwire.tsykunov.R
import ru.asshands.softwire.tsykunov.models.Post
import ru.asshands.softwire.tsykunov.database.Repository
import ru.asshands.softwire.tsykunov.services.RetrofitModule
import ru.asshands.softwire.tsykunov.utils.ArrowBehavior
import java.io.IOException
import java.net.UnknownHostException

class RandomViewModel(
    private val repository: Repository // TODO History in the future
) : ViewModel() {

    private val mutablePost = MutableLiveData<Post>()
    val post: LiveData<Post> get() = mutablePost

    private val mutableNumPost = MutableLiveData<Int?>(-1)
    val numPost: LiveData<Int?> get() = mutableNumPost

    private val mutableErrorId = MutableLiveData<Int>()
    val errorId: LiveData<Int> get() = mutableErrorId

    private val postStorageList = mutableListOf<Post>()

    private var coroutineScope = CoroutineScope(Job() + Dispatchers.Main)
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(
            "StartViewModel",
            "Coroutine exception, scope active:${coroutineScope.isActive}",
            throwable
        )
        Log.e(
            "StartViewModel",
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

    fun loadPost(behavior: ArrowBehavior = ArrowBehavior.FORWARD) {
        when (behavior) {
            ArrowBehavior.BACK -> getPreviousPost()
            ArrowBehavior.FORWARD -> getNextPost()
        }
    }

    private fun getPreviousPost() {
        decNumPost()
        setCurrentPost()
    }

    private fun getNextPost() {
        if (numPost.value == null || numPost.value == postStorageList.lastIndex) {
            addRandomPostToStorage()
        } else {
            incNumPost()
            setCurrentPost()
        }
    }

    private fun setCurrentPost() {
        mutablePost.value = postStorageList[numPost.value!!]
    }

    private fun incNumPost() {
        mutableNumPost.value = mutableNumPost.value?.plus(1)
    }

    private fun decNumPost() {
        mutableNumPost.value = mutableNumPost.value?.minus(1)
    }

    private fun addRandomPostToStorage() {
        coroutineScope.launch(exceptionHandler) {
            postStorageList.add(RetrofitModule.devLifeApi.getRandomPost())
            incNumPost()
            setCurrentPost()
        }
    }

}