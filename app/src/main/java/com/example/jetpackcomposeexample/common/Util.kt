package com.example.jetpackcomposeexample.common

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
