package com.burakkodaloglu.my_e_commerce_app.di

import com.burakkodaloglu.my_e_commerce_app.data.repository.ProductRepositoryImpl
import com.burakkodaloglu.my_e_commerce_app.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindings {

    @Singleton
    @Binds
    abstract fun bindProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository
}