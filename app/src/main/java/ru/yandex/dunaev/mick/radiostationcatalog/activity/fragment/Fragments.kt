package ru.yandex.dunaev.mick.radiostationcatalog.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ru.yandex.dunaev.mick.radiostationcatalog.R
import ru.yandex.dunaev.mick.radiostationcatalog.databinding.CountriesFragmentBinding
import ru.yandex.dunaev.mick.radiostationcatalog.databinding.LanguagesFragmentBinding
import ru.yandex.dunaev.mick.radiostationcatalog.databinding.TagsFragmentBinding
import ru.yandex.dunaev.mick.radiostationcatalog.viewmodel.MainViewModel

class CountriesListFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<CountriesFragmentBinding>(inflater, R.layout.countries_fragment,container,false)
        val model: MainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        binding.viewModel = model
        return binding.root
    }
}

class LanguagesListFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<LanguagesFragmentBinding>(inflater, R.layout.languages_fragment,container,false)
        val model: MainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        binding.viewModel = model
        return binding.root
    }
}

class TagsListFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<TagsFragmentBinding>(inflater, R.layout.tags_fragment,container,false)
        val model: MainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        binding.viewModel = model
        return binding.root
    }
}