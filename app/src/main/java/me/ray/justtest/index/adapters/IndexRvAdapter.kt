package me.ray.justtest.index.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import me.ray.common.R
import me.ray.common.model.Result
import me.ray.common.viewmodel.BaseViewModel
import me.ray.justtest.databinding.HomeRvItemBinding

/**
 * Description: [me.ray.justtest.index.ui.IndexFragment]  Rv Adapter
 * Author : ray
 */
class IndexRvAdapter(
    val actionVM: BaseViewModel,
    var items: MutableList<Result> = mutableListOf()
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //获取相应的布局文件
        val layoutId = IndexRvHelper.getItemLayout(viewType)
        //数据绑定
        return ItemHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            ),
            viewType
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemHolder -> holder.bind(items[position])
        }
    }


    inner class ItemHolder(private val binding: ViewDataBinding, private val viewType: Int) :
        RecyclerView.ViewHolder(binding.root) {
        private val margin = itemView.context.resources.getDimension(R.dimen.header_padding).toInt()

        init {
            val layoutParams = itemView.layoutParams
            // 动态设置左右margin
            if (layoutParams is RecyclerView.LayoutParams) {
                if (layoutParams.leftMargin != margin) {
                    layoutParams.leftMargin = margin
                    layoutParams.rightMargin = margin
                    itemView.layoutParams = layoutParams
                }
            }
        }

        fun bind(item: Result) {
            when (binding) {

                is HomeRvItemBinding -> {
                    with(binding) {
                        result = item
                        viewModel = actionVM
                        executePendingBindings()
                    }
                }

            }

        }
    }


}