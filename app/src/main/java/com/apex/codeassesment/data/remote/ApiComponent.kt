package com.apex.codeassesment.data.remote

import com.apex.codeassesment.di.ApiModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun apiService(): RemoteDataSource
}