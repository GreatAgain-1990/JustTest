package me.ray.common.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import me.ray.common.R

/**
 * Description: 自定义字体TextView，修改字体
 * Author : ray
 */
class FontTextView(context: Context, attributeSet: AttributeSet? = null) :
    AppCompatTextView(context, attributeSet) {

    init {
        context.obtainStyledAttributes(attributeSet, R.styleable.FontTextView).run {
            if (hasValue(R.styleable.FontTextView_typeface)) {
                getString(R.styleable.FontTextView_typeface)?.let {
                    //从缓存中拿字体信息
                    this@FontTextView.typeface = FontCache.getTypeface(context, it)
                }

            }

            recycle()
        }
    }

}