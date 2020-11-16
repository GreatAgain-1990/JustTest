package me.ray.common.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.Serializable

/**
 * Description: viewModel基类，处理点击事件等
 * Author : ray
 */
abstract class BaseViewModel : ViewModel(), Serializable {

    protected val _onRefreshing = MutableLiveData<Boolean>()
    val onRefreshing: LiveData<Boolean> = _onRefreshing
    protected val _onError = MutableLiveData<Boolean>()
    val onError: LiveData<Boolean> = _onError

    /**
     * 首次进入获取数据
     */
    abstract fun fetchData(keywords: String?, page: Int)

    /**
     * 网络错误
     * 点击重试
     */
    open fun retryOnError(keywords: String) {
        fetchData(keywords, 1)
    }

    /**
     * 处理带链接跳转的
     */
    abstract fun jumpToC(view: View, actionUrl: String?)

}