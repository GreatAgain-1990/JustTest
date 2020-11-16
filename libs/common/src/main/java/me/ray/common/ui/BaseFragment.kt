package me.ray.common.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * Description:
 * Author : ray
 */
abstract class BaseFragment : Fragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 处理屏幕旋转等不请求重新请求数据
        if (savedInstanceState == null) {
            fetchData()
        }
    }

    abstract fun fetchData()

}