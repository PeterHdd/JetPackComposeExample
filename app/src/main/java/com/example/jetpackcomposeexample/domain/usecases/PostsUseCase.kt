package com.example.jetpackcomposeexample.domain.usecases

import com.example.jetpackcomposeexample.domain.repositories.PostsRepository
import com.example.jetpackcomposeexample.presentation.PostsDTO

class PostsUseCase constructor(private val postRepository: PostsRepository) {

     fun addPosts(){
        postRepository.addPost()
    }

    suspend fun fetchPosts(): List<PostsDTO> {
        return postRepository.fetchPosts()
    }
}