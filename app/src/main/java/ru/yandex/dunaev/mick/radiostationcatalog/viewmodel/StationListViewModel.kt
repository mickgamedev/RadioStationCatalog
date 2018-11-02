package ru.yandex.dunaev.mick.radiostationcatalog.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.yandex.dunaev.mick.radiostationcatalog.adapters.StationAdapter
import ru.yandex.dunaev.mick.radiostationcatalog.database.CatalogDatabase
import ru.yandex.dunaev.mick.radiostationcatalog.database.getStationsWithFilter
import ru.yandex.dunaev.mick.radiostationcatalog.model.StationModel

val TAG_SLVM = "StationListViewModel"

class StationListViewModel: ViewModel(){
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val stationsAdapter = StationAdapter()

    fun setFilter(filter: String){
        uiScope.launch{
            var listStation: List<StationModel>? = null
            withContext(Dispatchers.IO){
                listStation = CatalogDatabase.getStationsWithFilter(filter)
            }
            if(listStation != null) stationsAdapter.setItems(listStation!!)
            Log.w(TAG_SLVM,"station list getted")
        }
    }

    override fun onCleared() {
        viewModelJob.cancel()
    }
}