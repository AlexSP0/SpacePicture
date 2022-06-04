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

        fun setPresenter(presenter : PictureFragmentPresenter)

    }

    interface MainPresenter {
        fun attach(pictureFragment: MainView)
        fun getDailyImage()
        fun detach()

    }

    interface ByDatePresenter {
        fun attach(pictureFragment: MainView)
        fun getDailyImageByDate(date : String)
        fun getDailyImage()
        fun setDailyImage(response: NasaImageResponse)
        fun detach()
    }

    interface ByDateModel {
        fun loadImage()
        fun loadImageByDate(date : String)
        fun setPresenter(presenter : MainContracts.ByDatePresenter)

    }

}