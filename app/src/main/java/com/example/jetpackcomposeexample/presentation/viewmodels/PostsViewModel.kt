package com.example.jetpackcomposeexample.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.jetpackcomposeexample.domain.usecases.PostsUseCase
import com.example.jetpackcomposeexample.presentation.PostsDTO
import kotlinx.coroutines.Dispatchers

class PostsViewModel constructor(private val postsUseCase: PostsUseCase) : ViewModel() {

    private val TAG = PostsViewModel::class.java.name
    var listsOfPosts : LiveData<List<PostsDTO>>? = liveData(Dispatchers.IO) {
        emit(postsUseCase.fetchPosts())
     }
    }