package com.apex.codeassesment.utils

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.apex.codeassesment.R
import com.bumptech.glide.Glide

object BindingUtils {
    @BindingAdapter(value = ["loadImageFromUrl", "placeHolder"], requireAll = false)
    @JvmStatic
    fun loadImageFromUrl(
        imageView: ImageView,
        url: String? = null
    ) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(imageView.context).load(url).placeholder(R.drawable.image_place_holder).into(imageView)
        }
    }
}