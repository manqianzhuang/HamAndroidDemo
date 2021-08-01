package com.superman.animate.example.di.module

import com.superman.animate.example.http.Api
import com.superman.animate.example.http.HttpService
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton


@dagger.Module
class HttpModule {

    @Singleton
    @Provides
    fun provideApiService(): HttpService {
        return Api.default
    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return Api.okHttp
    }

//    @Singleton
//    @Provides
//    fun provideLogInterceptor(): Interceptor {
//        return LogInterceptor()
//    }

//    @Singleton
//    @Provides
//    fun provideRepo(apiService: HttpService): HttpRepo {
//        return HttpRepo(apiService)
//    }



}