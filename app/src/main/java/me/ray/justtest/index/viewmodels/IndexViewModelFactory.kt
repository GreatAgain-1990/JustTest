package me.ray.justtest.index.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.ray.common.ApiService
import me.ray.common.db.MyDatabase
import me.ray.justtest.index.repository.IndexRepository

/**
 * Description: HomeViewModel工厂类
 * Author : ray
 */
class IndexViewModelFactory(private val repository: IndexRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return IndexViewModel(repository) as T
    }

    companion object {
        fun get(context: Context): IndexViewModelFactory =
            IndexViewModelFactory(
                IndexRepository(ApiService.instance, MyDatabase.get(context).homeDAO())
            )
    }
}