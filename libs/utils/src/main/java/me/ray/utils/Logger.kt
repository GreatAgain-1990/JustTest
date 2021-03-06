package me.ray.utils

import android.util.Log

/**
 * Description: 日志工具类
 * Author : ray
 */
//应用级别日志tag
const val APP_TAG = "justtest"
//分级日志
inline fun <reified T> T.logi(msg :Any) = Log.i("$APP_TAG-${T::class.java.simpleName}", msg.toString())

//fun logd(tag :String, msg :String) = Log.d("$APP_TAG-$tag", msg)
inline fun <reified T> T.logd(msg :Any) = Log.d("$APP_TAG-${T::class.java.simpleName}", msg.toString())

//fun loge(tag :String, msg :String) = Log.e("$APP_TAG-$tag", msg)
inline fun <reified T> T.loge( msg :Any) = Log.e("$APP_TAG-${T::class.java.simpleName}", msg.toString())

inline fun <reified T> T.loge(tr : Throwable) = Log.e("$APP_TAG-${T::class.java.simpleName}", tr.message, tr)
