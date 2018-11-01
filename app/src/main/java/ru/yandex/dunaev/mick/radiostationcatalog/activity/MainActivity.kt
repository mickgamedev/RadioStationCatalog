package ru.yandex.dunaev.mick.radiostationcatalog.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ru.yandex.dunaev.mick.radiostationcatalog.viewmodel.MainViewModel
import ru.yandex.dunaev.mick.radiostationcatalog.R
import ru.yandex.dunaev.mick.radiostationcatalog.database.CatalogDatabase
import ru.yandex.dunaev.mick.radiostationcatalog.database.getSyncResult
import ru.yandex.dunaev.mick.radiostationcatalog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val model: MainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = model
        CatalogDatabase.getSyncResult().observe(this, Observer { res -> model.onSyncComplete(res[0]) })
    }
}
