package com.example.jetpackcomposeexample.presentation.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.jetpackcomposeexample.common.Resource
import com.example.jetpackcomposeexample.domain.usecases.PostsUseCase
import com.example.jetpackcomposeexample.presentation.PostsDTO
import kotlinx.coroutines.Dispatchers


class PostsViewModel @ViewModelInject constructor(private val postsUseCase: PostsUseCase) : ViewModel() {

    fun addPost(value : String) : LiveData<Resource<String?>> = liveData(Dispatchers.Main) {
        val postDTO = PostsDTO(value)
        emit(postsUseCase.addPosts(postDTO))
        listsOfPosts?.value = listOf(postDTO)
    }

    private val listsOfPosts : MutableLiveData<List<PostsDTO>>? = MutableLiveData(listOf(PostsDTO("message")))
    val posts : LiveData<Resource<List<PostsDTO>>>?  = listsOfPosts?.switchMap {
        liveData(Dispatchers.IO) {
            emit(postsUseCase.fetchPosts())
        }
    }
}