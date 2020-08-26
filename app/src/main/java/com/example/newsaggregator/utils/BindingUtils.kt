package com.example.newsaggregator.utils

import android.graphics.Color
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.lang.Exception

object BindingUtils {

    @BindingAdapter("imageSrc")
    @JvmStatic
    fun loadImage(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(imageUrl)
            .apply(RequestOptions())
            .into(view)
    }

    @BindingAdapter("color")
    @JvmStatic
    fun favoriteColorSelector(view: ImageView, isFavorite: Boolean) {
        view.setBackgroundColor(if (isFavorite) Color.YELLOW else Color.WHITE)
    }

}