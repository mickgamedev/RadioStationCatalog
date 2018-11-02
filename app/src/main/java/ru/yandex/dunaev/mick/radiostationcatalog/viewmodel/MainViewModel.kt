package ru.yandex.dunaev.mick.radiostationcatalog.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.yandex.dunaev.mick.radiostationcatalog.adapters.CountryAdapter
import ru.yandex.dunaev.mick.radiostationcatalog.adapters.LanguageAdapter
import ru.yandex.dunaev.mick.radiostationcatalog.adapters.TagAdapter
import ru.yandex.dunaev.mick.radiostationcatalog.database.*
import ru.yandex.dunaev.mick.radiostationcatalog.model.*

val TAG_MVM = "MainViewModel"

class MainViewModel: ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val disp = CatalogDatabase.getSyncResult().subscribe( {v -> onSyncComplete(v[0])},{err -> Log.e(TAG_MVM,err.message)},{})

    val countriesAdapter = CountryAdapter()
    val languagesAdapter = LanguageAdapter()
    val tagsAdapter = TagAdapter()

    var onShowStationWithFilter: (String) -> Unit = {v -> Log.v(TAG_MVM, "show stations not binding filter:$v")}

    init {
        countriesAdapter.onItemClick = { v -> onCountryClicked(v)}
        languagesAdapter.onItemClick = { v -> onLanguageClicked(v)}
        tagsAdapter.onItemClick = { v -> onTagClicked(v)}
    }

    fun syncNow() = syncDb()

    fun onSyncComplete(sync: SyncResult){
        Log.w(TAG_MVM, "${sync.date} insert:${sync.insert} update:${sync.update} delete:${sync.delete}")
        uiScope.launch{
            var countries: List<Country>? = null
            var languages: List<Language>? = null
            var tags: List<Tag>? = null
            withContext(Dispatchers.IO){
                countries = CatalogDatabase.getAllCountries()
                languages = CatalogDatabase.getAllLanguages()
                tags = CatalogDatabase.getAllTags()
            }
            if(countries != null) countriesAdapter.setItems(countries!!)
            if(languages != null) languagesAdapter.setItems(languages!!)
            if(tags != null) tagsAdapter.setItems(tags!!)
        }
    }

    fun onCountryClicked(position: Int){
        val filter = countriesAdapter.getItem(position).value
        onShowStationWithFilter("country='$filter'")
    }

    fun onLanguageClicked(position: Int){
        val filter = languagesAdapter.getItem(position).value
        onShowStationWithFilter("language='$filter'")
    }

    fun onTagClicked(position: Int){
        val filter = tagsAdapter.getItem(position).value
        onShowStationWithFilter("tags='$filter'")
    }

    override fun onCleared() {
        disp.dispose()
        viewModelJob.cancel()
    }
}