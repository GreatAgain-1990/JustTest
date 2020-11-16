package me.ray.common.db.converter

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import me.ray.common.model.Result
import me.ray.utils.JsonUtil

/**
 * Description:
 * Author : ray
 */
class HomeItemConverter {

    @TypeConverter
    fun itemListToString(items: List<Result>):String {
        return JsonUtil.toJson(items)
    }

    @TypeConverter
    fun stringToItemList(json : String) : List<Result> {
        val itemsType = object : TypeToken<List<Result>>(){}.type
        return JsonUtil.fromGson(json, itemsType)
    }

}