package com.example.newsaggregator.utils

import android.graphics.Color
import android.graphics.PorterDuff
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsaggregator.R
import java.lang.Exception

object BindingUtils {

    @BindingAdapter("loadImage")
    @JvmStatic
    fun loadImage(view: ImageView, imageUrl: String?) {
        try {
            Glide.with(view.context)
                .load(imageUrl)
                .apply(RequestOptions().centerCrop())
                .into(view)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @BindingAdapter("favoriteColorSelector")
    @JvmStatic
    fun favoriteColorSelector(view: ImageView, isFavorite: Boolean) {
        view.background = view.resources.getDrawable(if (isFavorite) R.drawable.ic_star_filled else R.drawable.ic_star_empty)
    }

}