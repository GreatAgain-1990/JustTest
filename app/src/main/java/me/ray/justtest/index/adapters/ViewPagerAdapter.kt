package me.ray.justtest.index.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import me.ray.justtest.index.ui.IndexFragment
import me.ray.justtest.index.ui.UrlFragment

/**
 * Description: 首页viewpager2适配器
 * Author : ray
 */
class ViewPagerAdapter(fragment: FragmentActivity, private val tabSize : Int) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount() = tabSize

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> IndexFragment.newInstance()
            1 -> UrlFragment.newInstance()
            else -> throw IndexOutOfBoundsException()
        }
    }
}