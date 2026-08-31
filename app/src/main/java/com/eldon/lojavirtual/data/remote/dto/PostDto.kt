package com.eldon.lojavirtual.data.remote.dto

import com.eldon.lojavirtual.domain.model.Post

data class PostDto(
    val id: Int,
    val title: String,
    val body: String,
    val tags: List<String>,
    val reactions: PostReactionsDto,
    val views: Int,
    val userId: Int
) {

    fun toDomain(): Post {
        return Post(
            id = id,
            title = title,
            body = body,
            tags = tags,
            reactions = reactions.toDomain(),
            views = views,
            userId = userId
        )
    }
}