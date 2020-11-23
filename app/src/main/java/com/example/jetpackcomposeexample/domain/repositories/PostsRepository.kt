package com.example.jetpackcomposeexample.domain.repositories

import com.example.jetpackcomposeexample.common.Resource
import com.example.jetpackcomposeexample.domain.entities.PostsEntity
import com.example.jetpackcomposeexample.presentation.PostsDTO

interface PostsRepository {
    suspend fun fetchPosts() : Resource<List<PostsDTO>>
    suspend fun addPost(postEntity: PostsEntity) : Resource<String?>
}