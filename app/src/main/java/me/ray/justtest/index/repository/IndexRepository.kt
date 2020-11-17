package me.ray.justtest.index.repository

import me.ray.common.Api
import me.ray.common.db.HomeDAO
import me.ray.common.model.ApiSearchV5
import okhttp3.RequestBody

/**
 * Description: 首页Repository，协调数据获取
 * Author : ray
 */
class IndexRepository(private val apiService: Api,private  val homeDAO: HomeDAO) {

    suspend fun fetchDiscovery(paramBody: RequestBody) = apiService.fetchSearch(paramBody)

    fun getFromDb()=homeDAO.getHomeData()

    fun saveInDb(apiSearchV5: ApiSearchV5) = homeDAO.save(apiSearchV5)


}