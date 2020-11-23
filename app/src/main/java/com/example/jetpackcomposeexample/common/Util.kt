package com.example.jetpackcomposeexample.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.jetpackcomposeexample.domain.entities.PostsEntity
import com.example.jetpackcomposeexample.domain.entities.UserInfoEntity
import com.example.jetpackcomposeexample.presentation.PostsDTO
import com.example.jetpackcomposeexample.presentation.UserInfoDTO

fun UserInfoDTO.toUserInfoEntity(userInfoDTO: UserInfoDTO): UserInfoEntity {
    return UserInfoEntity(name = userInfoDTO.name)
}

fun PostsDTO.toPostsEntity(postsDTO: PostsDTO): PostsEntity {
    return PostsEntity(messageBody = postsDTO.messageBody)
}

@Composable
internal fun centerView(item : @Composable () -> Unit){
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item()
    }
}