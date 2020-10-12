package com.example.jetpackcomposeexample.data

import com.example.jetpackcomposeexample.domain.repositories.PostsRepository
import com.example.jetpackcomposeexample.presentation.PostsDTO

class PostsRepositoryImpl constructor(private val postsDataSource: PostDataSource) : PostsRepository {
    override suspend fun fetchPosts(): List<PostsDTO> {
       return postsDataSource.fetchPosts()
    }

    override fun addPost() {
    }
}