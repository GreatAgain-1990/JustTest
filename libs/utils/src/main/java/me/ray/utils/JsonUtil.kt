package me.ray.utils

import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * Description:
 * Author : ray
 */
object JsonUtil {

    val gson : Gson = Gson()

    fun <T> fromGson(json: String, typeOfT : Type) = gson.fromJson<T>(json, typeOfT)

    fun toJson(src : Any) = gson.toJson(src)

}