package com.example.jetpackcomposeexample.domain.repositories

import com.example.jetpackcomposeexample.presentation.PostsDTO

interface PostsRepository {
    suspend fun fetchPosts() : List<PostsDTO>
    fun addPost()
}