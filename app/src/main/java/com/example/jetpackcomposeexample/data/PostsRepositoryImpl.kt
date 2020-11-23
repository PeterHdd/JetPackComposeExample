package com.example.jetpackcomposeexample.data

import com.example.jetpackcomposeexample.common.Resource
import com.example.jetpackcomposeexample.domain.entities.PostsEntity
import com.example.jetpackcomposeexample.domain.repositories.PostsRepository
import com.example.jetpackcomposeexample.presentation.PostsDTO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRepositoryImpl @Inject constructor(private val postsDataSource: PostDataSource) : PostsRepository {
    override suspend fun fetchPosts(): Resource<List<PostsDTO>> {
       return postsDataSource.fetchPosts()
    }

    override suspend fun addPost(postEntity: PostsEntity) : Resource<String?> {
        return postsDataSource.addPost(postEntity)
    }
}