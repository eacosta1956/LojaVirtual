package com.eldon.lojavirtual.domain.model

class Post {
}package com.eldon.lojavirtual.domain.model

data class Post(
    val id: Int,
    val title: String,
    val body: String,
    val tags: List<String>,
    val reactions: PostReactions,
    val views: Int,
    val userId: Int
)