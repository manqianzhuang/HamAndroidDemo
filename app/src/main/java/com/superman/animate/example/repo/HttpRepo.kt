package com.superman.animate.example.repo

import com.superman.animate.example.bean.BannerBean
import com.superman.animate.example.bean.BaseBean
import com.superman.animate.example.bean.IndexBean
import com.superman.animate.example.http.HttpService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HttpRepo @Inject constructor(private var apiService: HttpService){

    suspend fun getBanners() = apiService.getBanners<BannerBean>()
    suspend fun getIndexList(page: Int): Flow<BaseBean<IndexBean>> = flow {
        emit(apiService.getIndexList<IndexBean>(page))
    }.flowOn(Dispatchers.IO)
}