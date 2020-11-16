package me.ray.utils.extensions

import android.content.res.Resources
import android.graphics.Bitmap
import android.util.TypedValue
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import java.util.regex.Pattern

/**
 * Description: kotlin扩展方法
 * Author : ray
 */

/**
 * 根据手机的分辨率将dp转成为px。
 */
val Float.dp2px
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

/**
 * 图片加载扩展方法
 */
fun ImageView.load(
    url: String,
    trans: Transformation<Bitmap>? = null,
    placeHolderId: Int = 0
) {
    if (trans == null) {
        Glide.with(this)
            .load(url)
            .placeholder(placeHolderId)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    } else {
        Glide.with(this)
            .load(url)
            .placeholder(placeHolderId)
            .apply(
                RequestOptions.bitmapTransform(trans)
            )
            .into(this)
    }
}

/**
 * 图片加载扩展方法
 */
fun ImageView.load(
    resId: Int,
    trans: Transformation<Bitmap>? = null,
    placeHolderId: Int = 0,
) {
    if (trans == null) {
        Glide.with(this)
            .load(resId)
            .placeholder(placeHolderId)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    } else {
        Glide.with(this)
            .load(resId)
            .placeholder(placeHolderId)
            .apply(
                RequestOptions.bitmapTransform(trans)
            )
            .into(this)
    }
}

val a= arrayOf(  "top", "com.cn", "com", "net", "cn", "cc", "gov", "cn", "hk");

/**
 * 判断是否为正确的URL
 */
fun String.isRightUrl(): Boolean {
    var sb = StringBuilder();
    sb.append("(");
    for (f in a) {
        sb.append(f);
        sb.append("|");
    }
    sb.deleteCharAt(sb.length - 1);
    sb.append(")");

    val p = Pattern.compile("((https?|s?ftp|irc[6s]?|git|afp|telnet|smb)://)?((\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})|((www\\.|[a-zA-Z\\.\\-]+\\.)?[a-zA-Z0-9\\-]+\\." + sb.toString() + "(:[0-9]{1,5})?))((/[a-zA-Z0-9\\./,;\\?'\\+&%\\$#=~_\\-]*)|([^\\u4e00-\\u9fa5\\s0-9a-zA-Z\\./,;\\?'\\+&%\\$#=~_\\-]*))", Pattern.CASE_INSENSITIVE);
    val matcher = p.matcher(this);
    return matcher.matches()
}
