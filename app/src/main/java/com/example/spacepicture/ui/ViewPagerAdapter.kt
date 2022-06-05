package com.example.spacepicture.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

private const val PICTURE_BY_DATE = 0
private const val PICTURE_DAY = 1

class ViewPagerAdapter(private  val fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    private val fragments = arrayOf(PictureByDate(), PictureFragment())

    override fun getCount()= fragments.size

    override fun getItem(position: Int): Fragment {
        return when(position){
            PICTURE_BY_DATE -> fragments[PICTURE_BY_DATE] as Fragment
            PICTURE_DAY -> fragments[PICTURE_DAY] as Fragment
            else -> fragments[PICTURE_DAY] as Fragment
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            PICTURE_BY_DATE ->"PICTURE BY DATE"
            PICTURE_DAY -> "TODAY'S PICTURE"
            else -> "PICTURE_DAY"
        }
    }
}