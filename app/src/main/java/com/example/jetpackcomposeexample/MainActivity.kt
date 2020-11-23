package com.example.jetpackcomposeexample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import androidx.compose.ui.platform.setContent

import com.example.jetpackcomposeexample.presentation.viewmodels.PostsViewModel
import androidx.compose.ui.focus.ExperimentalFocus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
     private val viewModel: PostsViewModel by viewModels()

    @ExperimentalFocus
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            toNav(viewModel, this)
            }
        }
    }
