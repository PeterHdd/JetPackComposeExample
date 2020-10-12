package com.example.jetpackcomposeexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.Companion.align
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp

import androidx.lifecycle.ViewModelProvider
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexample.components.JetPackComposeExampleAppBar
import com.example.jetpackcomposeexample.data.PostDataSource
import com.example.jetpackcomposeexample.data.PostsRepositoryImpl
import com.example.jetpackcomposeexample.domain.usecases.PostsUseCase
import com.example.jetpackcomposeexample.presentation.viewmodels.PostsViewModel
import com.example.jetpackcomposeexample.presentation.viewmodels.PostsViewModelFactory
import com.example.jetpackcomposeexample.theme.JetPackComposeExampleTheme
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.koduok.compose.navigation.Router
import com.koduok.compose.navigation.core.backStackController


class MainActivity : AppCompatActivity() {
    lateinit var viewModel : PostsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          viewModel = ViewModelProvider(this, PostsViewModelFactory(postsUseCase = PostsUseCase(
              PostsRepositoryImpl(PostDataSource(db = FirebaseFirestore.getInstance()))
          ))).get(PostsViewModel::class.java)
        setContent {
            JetPackComposeExampleTheme{
                Scaffold(topBar = {
                    mainAppBar()
                }, bodyContent = {
                    usersPosts(viewModel, this)
                }, floatingActionButton = {
                    navigateToPostRoute()
                })
            }
        }
    }

    override fun onBackPressed() {
        if (!backStackController.pop()) super.onBackPressed()
    }
}
@Composable
fun usersPosts(viewModel: PostsViewModel, context : AppCompatActivity) {
    viewModel.listsOfPosts?.observeAsState()?.value?.let {
        if (it.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("No Posts Available")
            }
        }
    }
}


@Composable
fun mainAppBar(){
    val imageModifier = Modifier.align(Alignment.CenterVertically).preferredSize(35.dp)
            .drawShadow(elevation = 0.1.dp, shape = CircleShape)
            .background(color = Color.White, shape = CircleShape)

    JetPackComposeExampleAppBar(modifier = Modifier.align(Alignment.CenterVertically), backgroundColor = Color.White, actions = {
        Row(modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically),
                horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(asset = imageResource(R.drawable.peter), modifier = imageModifier)
            ProvideEmphasis(EmphasisAmbient.current.medium) {
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
fun SimpleScreen() {
//    SampleScreenRouter("A")
}

@Composable
fun navigateToPostRoute(){
    val navigateState = remember { mutableStateOf(false) }
    FloatingActionButton(onClick = { navigateState.value = true }, modifier = Modifier.align(Alignment.Bottom)) {
        Icon(asset = Icons.Filled.Add)
    }
    if(navigateState.value){

        }
    }

@Preview
@Composable
fun DefaultPreview() {
    mainAppBar()
}