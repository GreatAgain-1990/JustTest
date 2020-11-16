package me.ray.common.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import me.ray.common.R
import me.ray.utils.extensions.dp2px
import me.ray.utils.extensions.load


/**
 * cover图片，圆角
 */
@BindingAdapter("cover")
fun bindCover(imageView: ImageView, url: String?) {
    url ?: return
    imageView.load(
        url,
        RoundedCorners(defaultRoundRadius().toInt()),
        placeHolderId = R.drawable.shape_bg_album_loading
    )
}


//获取默认的圆角尺寸
fun defaultRoundRadius() = 5f.dp2px


