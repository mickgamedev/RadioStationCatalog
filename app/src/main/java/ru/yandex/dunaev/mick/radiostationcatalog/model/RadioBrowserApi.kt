package ru.yandex.dunaev.mick.radiostationcatalog.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST


interface RadioBrowserApi {
    @POST("json/stations")
    fun getStationList(): Call<List<StationModel>>

    companion object Factory{
        fun create(): RadioBrowserApi = Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.radio-browser.info/webservice/")
            .build()
            .create(RadioBrowserApi::class.java)
    }
}