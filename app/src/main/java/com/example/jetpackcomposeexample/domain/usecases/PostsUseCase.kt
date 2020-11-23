package com.example.jetpackcomposeexample.domain.usecases

import com.example.jetpackcomposeexample.common.Resource
import com.example.jetpackcomposeexample.common.toPostsEntity
import com.example.jetpackcomposeexample.domain.entities.PostsEntity
import com.example.jetpackcomposeexample.domain.repositories.PostsRepository
import com.example.jetpackcomposeexample.presentation.PostsDTO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsUseCase @Inject constructor(private val postRepository: PostsRepository) {

     suspend fun addPosts(postDTO: PostsDTO) : Resource<String?> {
         val postEntity : PostsEntity = postDTO.toPostsEntity(postDTO)
        return postRepository.addPost(postEntity)
    }

    suspend fun fetchPosts(): Resource<List<PostsDTO>> {
        return postRepository.fetchPosts()
    }
}