package com.example.nestedrecyclerview.data.model

data class Post(
    val userName: String,
    val userImage: String,
    val postDate: String,
    val postTitle: String,
    val postImage: String,
    val postLikesCount: Long,
    val postCommentCount: Long,
    val postShareCount: Long,
    val postIsLiked: Boolean
)
