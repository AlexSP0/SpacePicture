package com.example.spacepicture.model

import com.example.spacepicture.BuildConfig
import com.example.spacepicture.contracts.MainContracts
import com.example.spacepicture.data.NasaImageResponse
import com.example.spacepicture.data.NasaQueryDailyPicture
import com.example.spacepicture.presenters.PictureFragmentPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ByDateModelImpl : MainContracts.ByDateModel {
    private lateinit var presenter: MainContracts.ByDatePresenter

    private val baseUrl = "https://api.nasa.gov"

    override fun loadImage() {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val query: NasaQueryDailyPicture = retrofit.create(NasaQueryDailyPicture::class.java)
        query.loadDailyImage(BuildConfig.NASA_API_KEY).enqueue(object :
            Callback<NasaImageResponse> {
            override fun onResponse(
                call: Call<NasaImageResponse>,
                response: Response<NasaImageResponse>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    presenter.setDailyImage(body)
                }
            }

            override fun onFailure(call: Call<NasaImageResponse>, t: Throwable) {
                //Ooopps..
            }
        })
    }

    override fun loadImageByDate(date: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val query: NasaQueryDailyPicture = retrofit.create(NasaQueryDailyPicture::class.java)
        query.loadDailyImageByDate(BuildConfig.NASA_API_KEY, date).enqueue(object :
            Callback<NasaImageResponse> {
            override fun onResponse(
                call: Call<NasaImageResponse>,
                response: Response<NasaImageResponse>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    presenter.setDailyImage(body)
                }
            }

            override fun onFailure(call: Call<NasaImageResponse>, t: Throwable) {
                //Ooopps..
            }
        })
    }

    override fun setPresenter(presenter : MainContracts.ByDatePresenter) {
        this.presenter = presenter
    }

}