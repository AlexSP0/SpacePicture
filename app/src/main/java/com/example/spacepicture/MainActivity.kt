package com.example.spacepicture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.spacepicture.ui.PictureFragment

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SpacePicture)
        setContentView(R.layout.activity_main)
        loadFragment(PictureFragment())
        initMainMenu()
    }

    private fun initMainMenu() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.dark_theme -> {
                setTheme(R.style.Theme_SpacePicture)
                Log.d("act", "dark")
                recreate()
            }
            R.id.light_theme -> {
                setTheme(R.style.Theme_SpacePictureLight)
                Log.d("act", "light")
                recreate()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(fragment.toString())
            .commit()
    }
}