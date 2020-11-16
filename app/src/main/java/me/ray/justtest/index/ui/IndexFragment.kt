package me.ray.justtest.index.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import me.ray.common.databinding.FragmentRvWithFreshBinding
import me.ray.common.model.RequestBean
import me.ray.common.model.SearchArguments
import me.ray.common.model.Variables
import me.ray.common.ui.BaseFragment
import me.ray.justtest.index.adapters.IndexRvAdapter
import me.ray.justtest.index.viewmodels.IndexViewModel
import me.ray.justtest.index.viewmodels.IndexViewModelFactory
import me.ray.utils.logd
import me.ray.utils.loge
import me.ray.utils.logi
import me.ray.utils.ui.decoration.MarginDecoration
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * Description: 发现
 * Author : ray
 */
class IndexFragment : BaseFragment() {

    private val viewModel by activityViewModels<IndexViewModel> {
        IndexViewModelFactory.get(requireContext())
    }

    private lateinit var binding: FragmentRvWithFreshBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentRvWithFreshBinding.inflate(inflater, container, false).run {
            binding = this
            viewModel = this@IndexFragment.viewModel
            lifecycleOwner = viewLifecycleOwner

            with(refreshLayout) {
                val layoutParams = layoutParams as ConstraintLayout.LayoutParams
                layoutParams.leftMargin = 0
                layoutParams.rightMargin = 0
                this.layoutParams = layoutParams
            }

            binding.recyclerView.run {
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(
                    MarginDecoration(
                        top = context.resources.getDimension(me.ray.common.R.dimen.header_padding)
                            .toInt(),
                        bottom = context.resources.getDimension(me.ray.common.R.dimen.rv_divider_bottom)
                            .toInt()
                    )
                )

                adapter = IndexRvAdapter(this@IndexFragment.viewModel).also {
                    subscribeUi(it)
                }
            }

            binding.recyclerView.setOnBottomListener {
                page += 1
                (viewModel as IndexViewModel).fetchData(keywords,  page)
            }

            root
        }
    }

    override fun fetchData() {
        binding.page = 1
        viewModel.fetchData(binding.etSearch.text.toString(), binding.page)
    }


    /**
     * 观察数据变化
     */
    private fun subscribeUi(adapter: IndexRvAdapter) {
        logd("subscribe")
        viewModel.searchData.observe(viewLifecycleOwner, Observer {
            loge("data change...")
            binding.refreshLayout.isRefreshing = false

            if (binding.page == 1) {
                adapter.items.clear()
            }
            adapter.items.addAll(it)
            adapter.notifyDataSetChanged()
        })


    }

    companion object {
        @JvmStatic
        fun newInstance() = IndexFragment()
    }

}