package ru.yandex.dunaev.mick.radiostationcatalog.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ru.yandex.dunaev.mick.radiostationcatalog.R
import ru.yandex.dunaev.mick.radiostationcatalog.databinding.CountryCardBinding
import ru.yandex.dunaev.mick.radiostationcatalog.databinding.LanguageCardBinding
import ru.yandex.dunaev.mick.radiostationcatalog.databinding.StationCardBinding
import ru.yandex.dunaev.mick.radiostationcatalog.databinding.TagCardBinding
import ru.yandex.dunaev.mick.radiostationcatalog.model.Country
import ru.yandex.dunaev.mick.radiostationcatalog.model.Language
import ru.yandex.dunaev.mick.radiostationcatalog.model.StationModel
import ru.yandex.dunaev.mick.radiostationcatalog.model.Tag

class CountryAdapter : CategoryAdapter<Country, CountryCardBinding>(R.layout.country_card) {
    override fun onBindViewHolder(holder: CategoryHolder<CountryCardBinding>, position: Int) = with(holder.binding){
        holder.itemView.setOnClickListener { onItemClick(position) }
        data = getItem(position)
        executePendingBindings()
    }
}

class LanguageAdapter : CategoryAdapter<Language, LanguageCardBinding>(R.layout.language_card) {
    override fun onBindViewHolder(holder: CategoryHolder<LanguageCardBinding>, position: Int) = with(holder.binding){
        holder.itemView.setOnClickListener { onItemClick(position) }
        data = getItem(position)
        executePendingBindings()
    }
}

class TagAdapter : CategoryAdapter<Tag, TagCardBinding>(R.layout.tag_card) {
    override fun onBindViewHolder(holder: CategoryHolder<TagCardBinding>, position: Int) = with(holder.binding){
        holder.itemView.setOnClickListener { onItemClick(position) }
        data = getItem(position)
        executePendingBindings()
    }
}

class StationAdapter : CategoryAdapter<StationModel, StationCardBinding>(R.layout.station_card) {
    override fun onBindViewHolder(holder: CategoryHolder<StationCardBinding>, position: Int) = with(holder.binding){
        holder.itemView.setOnClickListener { onItemClick(position) }
        data = getItem(position)
        executePendingBindings()
    }
}



abstract class  CategoryAdapter<T: Any,V: ViewDataBinding>(val layout: Int) : RecyclerView.Adapter<CategoryAdapter.CategoryHolder<V>>(){
    private var items = listOf<T>()
    var onItemClick: (Int) -> Unit = {v -> Log.v("NOT BINDING","v = $v")}

    fun setItems(list: List<T>){
        items = list
        notifyDataSetChanged()
        Log.w("ADAPTER","setItems list size = ${list.size}")
    }
    fun getItem(position: Int) = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder<V> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<V>(inflater, layout, parent,false)
        return CategoryHolder<V>(binding)
    }

    override fun getItemCount()= items.size

    class CategoryHolder<V: ViewDataBinding>(val binding: V): RecyclerView.ViewHolder(binding.root)
}