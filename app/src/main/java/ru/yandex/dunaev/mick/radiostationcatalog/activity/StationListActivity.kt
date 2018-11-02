package ru.yandex.dunaev.mick.radiostationcatalog.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ru.yandex.dunaev.mick.radiostationcatalog.R
import ru.yandex.dunaev.mick.radiostationcatalog.databinding.StationListActivityBinding
import ru.yandex.dunaev.mick.radiostationcatalog.viewmodel.StationListViewModel

val EXTRA_FILTR = "filterstationlist"

class StationListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:StationListActivityBinding  = DataBindingUtil.setContentView(this, R.layout.station_list_activity)
        val model: StationListViewModel = ViewModelProviders.of(this).get(StationListViewModel::class.java)
        binding.viewModel = model
        binding.layoutManager = LinearLayoutManager(this)

        val filter = intent.getStringExtra(EXTRA_FILTR)
        binding.toolbar.title = filter.substringAfter('\'').substringBefore('\'')
        model.setFilter(filter)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}