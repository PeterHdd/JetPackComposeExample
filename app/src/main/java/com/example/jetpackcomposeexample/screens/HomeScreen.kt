package com.example.jetpackcomposeexample.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.Companion.align
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.example.jetpackcomposeexample.R
import com.example.jetpackcomposeexample.common.Constants
import com.example.jetpackcomposeexample.common.Status
import com.example.jetpackcomposeexample.common.centerView
import com.example.jetpackcomposeexample.components.JetPackComposeExampleAppBar
import com.example.jetpackcomposeexample.presentation.viewmodels.PostsViewModel


    @Composable
    fun HomeScreen(viewModel: PostsViewModel, navController: NavHostController) {
        Scaffold(topBar = {
            mainAppBar()
        }, bodyContent = {
            usersPosts(viewModel)
        }, floatingActionButton = {
            navigateToPostRoute(navController)
        })
    }

    @Composable
    fun usersPosts(viewModel: PostsViewModel) {
        var isLoading = remember { mutableStateOf(true) }
        viewModel.posts?.observeAsState()?.value?.let {
            isLoading.value = false
            if (it.data!!.isEmpty()) {
                centerView {
                    Text(text = Constants.NO_POST_AVAILABLE)

                }
            }
            else if(it.status == Status.SUCCESS) {
                LazyColumnFor(items = it.data) { item ->
                    Row {
                        val imageModifier = Modifier.align(Alignment.CenterVertically).preferredSize(55.dp)
                                .drawShadow(elevation = 0.1.dp, shape = CircleShape)
                                .background(color = Color.White, shape = CircleShape)

                        Image(asset = imageResource(R.drawable.peter), modifier = imageModifier)
                        Column {
                                Text(Constants.USERNAME, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 10.dp))

                            item.messageBody?.let { it1 -> Text(text = it1, modifier = Modifier.padding(start = 10.dp)) }
                            Row(modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween) {
                                Icon(asset = Icons.Outlined.Create, modifier = Modifier.preferredSize(20.dp), tint = Color.Gray)
                                Icon(asset = Icons.Outlined.Share,tint = Color.Gray)
                                Icon(asset = Icons.Outlined.FavoriteBorder, modifier = Modifier.preferredSize(20.dp),tint = Color.Gray)
                                Icon(asset = Icons.Outlined.Refresh, modifier = Modifier.padding(end = 50.dp).preferredSize(20.dp),tint = Color.Gray)
                            }
                            Divider()
                        }

                    }

                }
            }
            else if(it.status == Status.ERROR) {
                AlertDialog(onDismissRequest ={} , title = {"Error"},text = {it.message}, buttons = {})
            }
        }
        if(isLoading.value) {
           centerView {
               CircularProgressIndicator()
           }
        }
    }

    @Composable
    fun mainAppBar(){
        JetPackComposeExampleAppBar(modifier = Modifier.align(Alignment.CenterVertically), backgroundColor = Color.White, actions = {
            Row(modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val imageModifier = Modifier.align(Alignment.CenterVertically).preferredSize(35.dp)
                        .drawShadow(elevation = 0.1.dp, shape = CircleShape)
                        .background(color = Color.White, shape = CircleShape)

                Image(asset = imageResource(R.drawable.peter), modifier = imageModifier)
                ProvideEmphasis(AmbientEmphasisLevels.current.medium) {
                    Image(asset = imageResource(R.drawable.twitter_logo_blue),
                        modifier = Modifier.preferredSize(35.dp)
                            .padding(start = 8.dp)
                            .align(Alignment.CenterVertically)
                    )
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Search)
                    }
                }
            }
        })
    }

    @Composable
    fun navigateToPostRoute(navController: NavHostController) {
        FloatingActionButton(
            onClick = {navController.navigate(Constants.ADD_POST)},
            modifier = Modifier.align(Alignment.Bottom)
        ) {
            Icon(asset = Icons.Filled.Add)
        }
    }

