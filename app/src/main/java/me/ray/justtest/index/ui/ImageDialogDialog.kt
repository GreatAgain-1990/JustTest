package me.ray.justtest.index.ui

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import me.ray.common.model.Result
import me.ray.justtest.R
import me.ray.justtest.databinding.DialogImageBinding

class ImageDialogDialog(context: Context) : Dialog(context, R.style.Theme_AppCompat_Dialog) {
    private var dataBinding: ViewDataBinding? = null
 
    init {
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_image, null, false)
        dataBinding?.root?.let { setContentView(it) }//核心代码
        window!!.attributes.width = WindowManager.LayoutParams.MATCH_PARENT
        window!!.attributes.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.setGravity(Gravity.BOTTOM)
        setCancelable(true)
        setCanceledOnTouchOutside(false)
    }
 
    fun initData(url: String) {

        (dataBinding as DialogImageBinding).apply {
            imageurl = url

            ivClose.setOnClickListener { dismiss() }

            executePendingBindings()

        }

    }

 
}