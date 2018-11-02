package ru.yandex.dunaev.mick.radiostationcatalog.viewmodel

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("src")
fun ImageView.loadImage(url: String) {
    if (!url.isEmpty()) Picasso.get().load(url).into(this)
}

@BindingAdapter("visibility")
fun View.visibilityFromInt(i: Int){
    visibility = if(i > 0) View.VISIBLE else View.GONE
}