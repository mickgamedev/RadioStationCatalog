package ru.yandex.dunaev.mick.radiostationcatalog

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    fun syncNow() = syncDb()
}