package com.example

import com.example.model.Post
import com.example.repository.post.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostInfo @Inject constructor(
    private val postRepository: PostRepository,
) {

    operator fun invoke(): Flow<List<Post>> {
        return postRepository.getPostInfo()
    }

    suspend fun getNewImages(): Result<Unit> {
        return postRepository.getNewImages()
    }
}