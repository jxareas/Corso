package com.jonareas.corso.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, url: String?) {
    view.loadImage(url, progressDrawable(view.context))
}