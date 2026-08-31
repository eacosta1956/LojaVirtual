package com.eldon.lojavirtual.data.remote.dto

import com.eldon.lojavirtual.domain.model.PostReactions

data class PostReactionsDto(
    val likes: Int,
    val dislikes: Int
) {

    fun toDomain(): PostReactions {
        return PostReactions(
            likes = likes,
            dislikes = dislikes
        )
    }
}