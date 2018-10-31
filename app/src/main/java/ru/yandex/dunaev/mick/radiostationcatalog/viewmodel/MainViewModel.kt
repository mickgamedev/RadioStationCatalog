package ru.yandex.dunaev.mick.radiostationcatalog.viewmodel

import androidx.lifecycle.ViewModel
import ru.yandex.dunaev.mick.radiostationcatalog.model.syncDb

class MainViewModel: ViewModel() {

    fun syncNow() = syncDb()
}