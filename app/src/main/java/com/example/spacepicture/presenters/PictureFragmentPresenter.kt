package com.example.spacepicture.presenters

import com.example.spacepicture.contracts.MainContracts
import com.example.spacepicture.data.NasaImageResponse
import com.example.spacepicture.model.PictureModelImpl

class PictureFragmentPresenter : MainContracts.MainPresenter {
    private var fragment: MainContracts.MainView? = null

    private val model : MainContracts.MainModel = PictureModelImpl()

    init {
        model.setPresenter(this)
    }

    override fun getDailyImage() {
        model.loadImage()
    }

    fun setDailyImage(response: NasaImageResponse) {
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