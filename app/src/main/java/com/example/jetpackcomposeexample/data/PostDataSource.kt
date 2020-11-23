package com.example.jetpackcomposeexample.data

import android.util.Log
import com.example.jetpackcomposeexample.common.Resource
import com.example.jetpackcomposeexample.domain.entities.PostsEntity
import com.example.jetpackcomposeexample.presentation.PostsDTO
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import dagger.Provides
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PostDataSource @Inject constructor(val db : FirebaseFirestore) {
    private val TAG = PostDataSource::class.java.name

    suspend fun fetchPosts(): Resource<List<PostsDTO>> {
        try {
            val postsList : MutableList<PostsDTO> = ArrayList<PostsDTO>()
            val posts = db.collection("Posts").get().await()
            for(documents in posts.documents){
                documents.toObject(PostsDTO::class.java)?.let {
                    postsList.add(it)
                }
            }
            return Resource.success(postsList)
        }catch (e: FirebaseFirestoreException) {
            Log.e(TAG, e.code.toString())
            Log.e(TAG,e.stackTrace.toString())
             return Resource.error(msg = "Error",listOf(PostsDTO(e.message)))

        }
    }

    suspend fun addPost(postEntity: PostsEntity): Resource<String?> {
        try {
            db.collection("Posts").add(postEntity).await()
            return Resource.success("success")
        } catch (e: FirebaseFirestoreException){
            Log.e(TAG,e.stackTraceToString())
            return Resource.error(msg = "Error",e.message)

        }
    }
}