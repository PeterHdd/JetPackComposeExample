package com.example.jetpackcomposeexample.di

import com.example.jetpackcomposeexample.data.PostsRepositoryImpl
import com.example.jetpackcomposeexample.domain.repositories.PostsRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module(includes = [PostModule::class])
@InstallIn(ApplicationComponent::class)
abstract class AppModule {

    @Binds
    abstract fun providePostRepository(postsRepositoryImpl: PostsRepositoryImpl): PostsRepository

}

@Module
@InstallIn(ApplicationComponent::class)
internal class PostModule {

    @Provides
    @Singleton
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()
}