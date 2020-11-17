package me.ray.justtest.index.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import me.ray.common.db.MyDatabase
import me.ray.common.model.RequestBean
import me.ray.common.model.Result
import me.ray.common.model.SearchArguments
import me.ray.common.model.Variables
import me.ray.common.viewmodel.BaseViewModel
import me.ray.justtest.index.repository.IndexRepository
import me.ray.justtest.index.ui.ImageDialogDialog
import me.ray.network.requestFlow
import me.ray.utils.logi
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * Description: 首页-推荐 viewModel
 * Author : ray
 */
@ExperimentalCoroutinesApi
class IndexViewModel(private val repository: IndexRepository) : BaseViewModel() {

    private val _searchData: MutableLiveData<List<Result>> = MutableLiveData()
    val searchData: LiveData<List<Result>> = _searchData

    /**
     * 获取搜索数据
     */
    override fun fetchData(keywords: String?, p: Int) {

        /**
         * 获取搜索数据，参数组装 post json请求的方式
         */
        var searchArg = SearchArguments(page = p, term = keywords?:"")
        var variables = Variables(searchArg)
        var requestBean = RequestBean(variables = variables)

        val gson = Gson()

        val route = gson.toJson(requestBean).apply {
            logi(">>>>>>>请求参数：$this")
            this
        }
        val paramBody = RequestBody.create(
            MediaType.parse("application/json;charset=UTF-8"),
            route
        )//将参数包装成RequestBody

        requestFlow({
            logi("数据库数据：${repository.getFromDb()}")

            repository.fetchDiscovery(paramBody)

        }, {
            _searchData.value = it.data.apiSearchV5?.results

            repository.saveInDb(it.data.apiSearchV5)

        }, onStart = {
            _onRefreshing.value = true
            _onError.value = false
        }, onComplete = {
            _onRefreshing.value = false
        }, onError = {
            _onError.value = true
        }).launchIn(viewModelScope)
    }

    override fun jumpToC(view: View, actionUrl: String?) {
        logi(">>>>>>>>显示弹窗")
        ImageDialogDialog(view.context).apply {
            initData(actionUrl ?: "")
            show()
        }
    }
}