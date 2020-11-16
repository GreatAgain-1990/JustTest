package me.ray.common

import me.ray.common.model.SearchData
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Description: 数据接口
 * Author : ray
 */
interface Api {

    /**
     * 获取首页-发现数据
     */
    @POST("_gateway/graphql")
    suspend fun fetchDiscovery(@Body route: RequestBody): SearchData


}