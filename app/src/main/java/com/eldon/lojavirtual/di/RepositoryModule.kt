package com.eldon.lojavirtual.di

import com.eldon.lojavirtual.data.repository.ProductRepositoryImpl
import com.eldon.lojavirtual.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.eldon.lojavirtual.data.repository.CartRepositoryImpl
import com.eldon.lojavirtual.domain.repository.CartRepository
import com.eldon.lojavirtual.data.repository.UserRepositoryImpl
import com.eldon.lojavirtual.domain.repository.UserRepository
import com.eldon.lojavirtual.data.repository.PostRepositoryImpl
import com.eldon.lojavirtual.domain.repository.PostRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        cartRepositoryImpl: CartRepositoryImpl
    ): CartRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindPostRepository(
        postRepositoryImpl: PostRepositoryImpl
    ): PostRepository
}