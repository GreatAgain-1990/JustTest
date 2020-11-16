package me.ray.common.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.ray.common.db.converter.HomeItemConverter
import me.ray.common.model.ApiSearchV5
import me.ray.common.model.Result

/**
 * Description: 数据库，缓存数据
 * Author : ray
 */
@Database(entities = [ApiSearchV5::class], version = 1, exportSchema = false)
@TypeConverters(HomeItemConverter::class)
abstract class MyDatabase : RoomDatabase() {
    //首页DAO
    abstract fun homeDAO(): HomeDAO


    companion object {
        lateinit var appContext: Context
        fun get(appContext: Context): MyDatabase {
            this.appContext = appContext.applicationContext
            return INSTANCE
        }

        private val INSTANCE: MyDatabase by lazy {
            Room.databaseBuilder(
                appContext,
                MyDatabase::class.java, "justdb"
            ).build()
        }
    }

}