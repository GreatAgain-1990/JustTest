package me.ray.justtest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import me.ray.justtest.index.adapters.ViewPagerAdapter
import me.ray.utils.SpUtil

/**
 * Description: 主页
 * Author : ray
 */
class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    private val tabs = listOf("首页", "URL搜索")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById<ViewPager2>(R.id.viewPagerHome).apply {
            adapter = ViewPagerAdapter(this@MainActivity, tabs.size)
        }
        tabLayout = findViewById<TabLayout>(R.id.tabHome).apply {
            //指示器高度
            setSelectedTabIndicator(
                ContextCompat.getDrawable(
                    this@MainActivity,
                    R.drawable.shape_tab_indicator
                )
            )
            //设置指示器的颜色
            setSelectedTabIndicatorColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.colorTextPrimary
                )
            )
            //设置文字颜色
            setTabTextColors(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.colorTextSecondary
                ),
                ContextCompat.getColor(this@MainActivity, R.color.colorTextPrimary)
            )
        }

        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        tabLayout.removeAllTabs()

        with(tabLayout) {
            for (tab in tabs) {
                addTab(newTab().apply {
                    customView = LayoutInflater.from(this@MainActivity)
                        .inflate(R.layout.tab_item, this@with, false)
                        .also {
                            it.findViewById<TextView>(R.id.tv_tab_title).text = tab
                        }
                })
            }
        }

        viewPager.currentItem = savedInstanceState?.let { SpUtil.getInt(this@MainActivity,
            TAB_INDEX
        ) }?:0
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        SpUtil.putInt(this, TAB_INDEX,viewPager.currentItem)
    }

    companion object {

        const val TAB_INDEX = "tab_home"
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }


}