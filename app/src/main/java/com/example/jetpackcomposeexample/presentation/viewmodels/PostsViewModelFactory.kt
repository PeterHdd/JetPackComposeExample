package com.example.jetpackcomposeexample.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposeexample.domain.usecases.PostsUseCase

class PostsViewModelFactory constructor(private val postsUseCase: PostsUseCase) : ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(p0: Class<T>): T {
        return p0.getConstructor(PostsUseCase::class.java).newInstance(postsUseCase)
    }
}