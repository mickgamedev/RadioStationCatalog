package ru.yandex.dunaev.mick.radiostationcatalog.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.yandex.dunaev.mick.radiostationcatalog.model.BaseEntity

@BindingAdapter("adapter")
fun RecyclerView.recyclerAdapter(adp: CategoryAdapter<BaseEntity, ViewDataBinding>){
    adapter = adp
}

@BindingAdapter("layout_manager")
fun RecyclerView.recyclerManager(manager: RecyclerView.LayoutManager){
    layoutManager = manager
}

@BindingAdapter("src")
fun ImageView.loadImage(url: String){
    Picasso.get().load(url).into(this)
}