package com.example.spacepicture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.spacepicture.ui.PictureFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(PictureFragment())
    }

    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(fragment.toString())
            .commit()
    }
}