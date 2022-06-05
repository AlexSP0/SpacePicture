package com.example.spacepicture.presenters

import com.example.spacepicture.contracts.MainContracts
import com.example.spacepicture.data.NasaImageResponse
import com.example.spacepicture.model.ByDateModelImpl
import com.example.spacepicture.model.PictureModelImpl

class ByDatePresenterImpl : MainContracts.ByDatePresenter {
    private var fragment: MainContracts.MainView? = null

    private val model : MainContracts.ByDateModel = ByDateModelImpl()

    init {
        model.setPresenter(this)
    }

    override fun getDailyImage() {
        model.loadImage()
    }

    override fun setDailyImage(response: NasaImageResponse) {
        fragment?.setImage(response)
    }

    override fun attach(fragment: MainContracts.MainView) {
        this.fragment = fragment
    }

    override fun getDailyImageByDate(date: String) {
        model.loadImageByDate(date)
    }

    override fun detach() {
        this.fragment = null
    }
}