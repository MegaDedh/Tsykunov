package ru.asshands.softwire.tsykunov.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Post (

    @SerialName("id")
    val id: Long,

    @SerialName("description")
    val description: String,

//    @SerialName("votes")
//    val votes: Long,
//
//    @SerialName("author")
//    val author: String,
//
//    @SerialName("date")
//    val date: String,
//
    @SerialName("gifURL")
    val gifURL: String? = null,
//
//    @SerialName("gifSize")
//    val gifSize: Long,
//
//    @SerialName("previewURL")
//    val previewURL: String,
//
//    @SerialName("videoURL")
//    val videoURL: String,
//
//    @SerialName("videoPath")
//    val videoPath: String,
//
//    @SerialName("videoSize")
//    val videoSize: Long,
//
//    @SerialName("type")
//    val type: String,
//
//    @SerialName("width")
//    val width: String,
//
//    @SerialName("height")
//    val height: String,
//
//    @SerialName("commentsCount")
//    val commentsCount: Long,
//
//    @SerialName("fileSize")
//    val fileSize: Long,
//
//    @SerialName("canVote")
//    val canVote: Boolean
        )