package com.superman.animate.example.http

import com.superman.animate.example.bean.*
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 网络请求接口
 * 注意：接口前无需加斜杠
 * create by Mqz at 4/19
 */
interface HttpService {

    companion object {
        //正式接口
        const val url = "https://www.wanandroid.com/"
    }

    @GET("article/list/{page}/json")
    suspend fun <T> getIndexList(@Path("page") page: Int): BaseBean<T>

    @GET("banner/json")
    suspend fun <T> getBanners(): BaseBean<T>

}
