package me.ray.common.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.ray.common.model.ApiSearchV5

/**
 * Description: 首页数据DAO类
 * Author : ray
 */
@Dao
interface HomeDAO {
    @Query("SELECT * FROM search")
    suspend fun getHomeData(): ApiSearchV5

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(homePage: ApiSearchV5)

}