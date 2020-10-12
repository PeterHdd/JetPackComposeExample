package com.example.jetpackcomposeexample.data

import android.util.Log
import com.example.jetpackcomposeexample.presentation.PostsDTO
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await
import java.util.*

class PostDataSource constructor(val db : FirebaseFirestore) {
    private val TAG = PostDataSource::class.java.name

    suspend fun fetchPosts(): List<PostsDTO> {
        try {
            val postsList : MutableList<PostsDTO> = ArrayList<PostsDTO>()
            val posts = db.collection("Posts").get().await()
            for(documents in posts.documents){
                documents.toObject(PostsDTO::class.java)?.let {
                    postsList.add(it)
                    print(postsList.toString())
                }
            }
            return postsList
        }catch (e: FirebaseFirestoreException) {
            Log.e(TAG, e.code.toString())
            Log.e(TAG,e.stackTrace.toString())
            throw e
        }
    }

    fun addPost(){

    }
}