package com.example.spacepicture.contracts

import com.example.spacepicture.presenters.PictureFragmentPresenter

class MainContracts {
    interface MainView {
        fun setImage()
    }

    interface MainModel {
        fun loadImage()

        fun setPresenter(presenter : PictureFragmentPresenter)

    }

    interface MainPresenter {

    }

}