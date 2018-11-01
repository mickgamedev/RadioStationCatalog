package ru.yandex.dunaev.mick.radiostationcatalog.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import ru.yandex.dunaev.mick.radiostationcatalog.R
import ru.yandex.dunaev.mick.radiostationcatalog.activity.fragment.CountriesListFragment
import ru.yandex.dunaev.mick.radiostationcatalog.activity.fragment.LanguagesListFragment
import ru.yandex.dunaev.mick.radiostationcatalog.activity.fragment.TagsListFragment
import ru.yandex.dunaev.mick.radiostationcatalog.databinding.ActivityMainBinding
import ru.yandex.dunaev.mick.radiostationcatalog.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val model: MainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = model

        setSupportActionBar(binding.toolbar)

        binding.pager.adapter = CatalogPagerAdapter(supportFragmentManager)
        binding.tabs.setupWithViewPager(binding.pager)
    }
}

class CatalogPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment = when(position){
        0 -> CountriesListFragment()
        1 -> LanguagesListFragment()
        2 -> TagsListFragment()
        else -> TODO("not implemented")
    }

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence? = when(position){
        0 -> "Country"
        1 -> "Language"
        2 -> "Tag"
        else -> TODO("not implemented")
    }
}
