package me.ray.common

import me.ray.utils.logi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okio.BufferedSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.Charset

/**
 * Description: api服务类
 * Author : ray
 */
class ApiService {

    class CommonParamsInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url()
            val url = originalHttpUrl.newBuilder().apply {

            }.build()
            val request = originalRequest.newBuilder().url(url)
                .method(originalRequest.method(), originalRequest.body()).build()
            return chain.proceed(request)
        }
    }

    class LoggingInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()

            //计算网络请求时间
            val t1 = System.currentTimeMillis()
            val response:okhttp3.Response = chain.proceed(request)

            val t2 = System.currentTimeMillis()
            logi(
                ">>>>>>>>>Received response for \n{${
                    response.request()
                        .url()
                }}\nin ${(t2 - t1)} ms"
            )

            logi(
                ">>>>>>>>>返回数据： ${response}"
            )

            val responseBody = response.body()

            val source: BufferedSource = responseBody!!.source()
            source.request(Long.MAX_VALUE) // Buffer the entire body.

            val buffer: okio.Buffer = source.buffer()
            logi(">>>>>>>>>response:" + buffer.clone().readString(Charset.forName("UTF-8")))
            return response
        }
    }

    companion object {
        private const val BASE_URL = "https://rapidapi.com/"

        private val httpClient = OkHttpClient.Builder()
            .addInterceptor(CommonParamsInterceptor())  //添加公共参数拦截器
            .addInterceptor(LoggingInterceptor())  //添加网络请求日志拦截器
            .build()

        val instance: Api by lazy {
            Retrofit.Builder()
                .client(httpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }

}