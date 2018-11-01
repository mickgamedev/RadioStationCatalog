package ru.yandex.dunaev.mick.radiostationcatalog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ru.yandex.dunaev.mick.radiostationcatalog.R
import ru.yandex.dunaev.mick.radiostationcatalog.databinding.CountryCardBinding
import ru.yandex.dunaev.mick.radiostationcatalog.databinding.LanguageCardBinding
import ru.yandex.dunaev.mick.radiostationcatalog.databinding.TagCardBinding
import ru.yandex.dunaev.mick.radiostationcatalog.model.BaseEntity
import ru.yandex.dunaev.mick.radiostationcatalog.model.Country
import ru.yandex.dunaev.mick.radiostationcatalog.model.Language
import ru.yandex.dunaev.mick.radiostationcatalog.model.Tag

class CountryAdapter : CategoryAdapter<Country, CountryCardBinding>(R.layout.country_card) {
    override fun onBindViewHolder(holder: CategoryHolder<Country, CountryCardBinding>, position: Int) = with(holder.binding){
        data = getItem(position)
        executePendingBindings()
    }
}

class LanguageAdapter : CategoryAdapter<Language, LanguageCardBinding>(R.layout.language_card) {
    override fun onBindViewHolder(holder: CategoryHolder<Language, LanguageCardBinding>, position: Int) = with(holder.binding){
        data = getItem(position)
        executePendingBindings()
    }
}

class TagAdapter : CategoryAdapter<Tag, TagCardBinding>(R.layout.tag_card) {
    override fun onBindViewHolder(holder: CategoryHolder<Tag, TagCardBinding>, position: Int) = with(holder.binding){
        data = getItem(position)
        executePendingBindings()
    }
}

abstract class  CategoryAdapter<T: BaseEntity,V: ViewDataBinding>(val layout: Int) : RecyclerView.Adapter<CategoryAdapter.CategoryHolder<T,V>>(){
    var items = listOf<T>()
    fun set(list: List<T>){
        items = list
        notifyDataSetChanged()
    }
    fun getItem(position: Int) = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder<T,V> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<V>(inflater, layout, parent,false)
        return CategoryHolder<T,V>(binding)
    }

    override fun getItemCount()= items.size

    class CategoryHolder<T: BaseEntity,V: ViewDataBinding>(val binding: V): RecyclerView.ViewHolder(binding.root)
}