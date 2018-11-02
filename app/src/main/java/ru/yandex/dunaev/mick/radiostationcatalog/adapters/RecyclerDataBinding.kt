package ru.yandex.dunaev.mick.radiostationcatalog.adapters

import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ru.yandex.dunaev.mick.radiostationcatalog.model.BaseEntity

@BindingAdapter("adapter")
fun RecyclerView.recyclerAdapter(adp: CategoryAdapter<BaseEntity, ViewDataBinding>){
    adapter = adp
}

@BindingAdapter("layout_manager")
fun RecyclerView.recyclerManager(manager: RecyclerView.LayoutManager){
    layoutManager = manager
}
