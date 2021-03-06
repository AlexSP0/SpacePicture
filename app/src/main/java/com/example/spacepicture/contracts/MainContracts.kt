package com.example.spacepicture.contracts

import com.example.spacepicture.data.NasaImageResponse
import com.example.spacepicture.presenters.PictureFragmentPresenter
import com.example.spacepicture.ui.PictureFragment

class MainContracts {

    interface MainView {
        fun setImage(response: NasaImageResponse)
    }

    interface MainModel {
        fun loadImage()

        fun loadImageByDate(date : String)

        fun setPresenter(presenter : PictureFragmentPresenter)

    }

    interface MainPresenter {
        abstract fun attach(pictureFragment: MainView)

        fun getDailyImageByDate(date : String)
        fun getDailyImage()
        fun detach()

    }

}