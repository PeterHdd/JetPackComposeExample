package com.example.jetpackcomposeexample.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.focus
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.jetpackcomposeexample.MainActivity
import com.example.jetpackcomposeexample.R
import com.example.jetpackcomposeexample.common.Constants
import com.example.jetpackcomposeexample.common.Resource
import com.example.jetpackcomposeexample.presentation.viewmodels.PostsViewModel

@ExperimentalFocus
@Composable
fun addPostScreen(
    viewModel: PostsViewModel,
    navController: NavController,
    mainActivity: MainActivity
){
    val btnState = remember { mutableStateOf(false) }
    val textState = remember { mutableStateOf(TextFieldValue()) }
    Scaffold(topBar = {
            addPost(btnState,navController, textState, viewModel, mainActivity)
        },bodyContent = {
            addPost(btnState,textState)
        })
}

@Composable
fun addPost(
    btnState: MutableState<Boolean>,
    navController: NavController,
    textState: MutableState<TextFieldValue>,
    viewModel: PostsViewModel,
    mainActivity: MainActivity
){
    val isSaved = remember { mutableStateOf(false) }
    Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {navController.popBackStack()}){
            Icon(asset = Icons.Filled.Close, tint = MaterialTheme.colors.primary, modifier = Modifier.padding(10.dp,15.dp,2.dp,2.dp))
        }
        Button(
            onClick = {
                saveToDb(viewModel = viewModel, textState = textState).observe(mainActivity,{
                    isSaved.value = true
                    navController.popBackStack()
                })
            },
            enabled = btnState.value,
            modifier = Modifier.padding(10.dp, 10.dp, 20.dp, 2.dp),
            shape = RoundedCornerShape(50)
        ) {
            Text(text = Constants.POST)
        }
    }
    if(isSaved.value){
        Snackbar {
            Text(Constants.POST_ADDED)
        }
    }
}


fun saveToDb(viewModel: PostsViewModel, textState: MutableState<TextFieldValue>): LiveData<Resource<String?>> {
     return viewModel.addPost(textState.value.text)
}

@ExperimentalFocus
@Composable
fun addPost(btnState: MutableState<Boolean>, textState: MutableState<TextFieldValue>) {

        val imageModifier = Modifier.preferredSize(45.dp)
            .drawShadow(elevation = 0.1.dp, shape = CircleShape)
            .background(color = Color.White, shape = CircleShape)
    val focusState = remember { mutableStateOf(FocusState.Inactive) }
    val focusRequester = FocusRequester()
    val focusModifier = Modifier.focus()
    Box(modifier = Modifier.padding(10.dp,15.dp,2.dp,2.dp)) {
        Row(modifier = Modifier.fillMaxWidth().focusObserver { focusState.value = it },
        ) {
            val focusRequesterModifier = Modifier.focusRequester(focusRequester)
            Image(asset = imageResource(R.drawable.peter), modifier = imageModifier)
            TextField(
                    modifier = focusModifier.then(focusRequesterModifier),
                    value = textState.value,
                    onValueChange = { value: TextFieldValue ->
                        textState.value = value
                        btnState.value = value.text.isNotEmpty()
                    },
                    placeholder = { Text(text = Constants.POST_PLACEHOLDER, color = Color.Gray) },
                    backgroundColor = Color.White,
            )
        }
        onActive {
            focusRequester.requestFocus()
        }
    }
}