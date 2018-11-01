package ru.yandex.dunaev.mick.radiostationcatalog.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST


interface RadioBrowserApi {
    @POST("json/stations")
    fun getStationList(): Call<List<StationModel>>

    @POST("json/countries")
    fun getCountiesList(): Call<List<Country>>

    @POST("json/languages")
    fun getLanguagesList(): Call<List<Language>>

    @POST("json/tags")
    fun getTagsList(): Call<List<Tag>>

    companion object Factory{
        private val radioBrowserApi = Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.radio-browser.info/webservice/")
            .build()
            .create(RadioBrowserApi::class.java)
        fun getApi(): RadioBrowserApi = radioBrowserApi
    }
}