package com.example.sharedpreferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import android.view.MenuItem
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.find
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

class MainActivity : AppCompatActivity() {

    var collapsinToolbar: CollapsingToolbarLayout? = null
    var preferencias: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        collapsinToolbar = find<CollapsingToolbarLayout>(R.id.collapsingToolbar)
        collapsinToolbar!!.title = "Beneficios de Kotlin"

        val toolbar = find<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolbar)

        preferencias = PreferenceManager.getDefaultSharedPreferences(this)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item!!.itemId) {

        R.id.salir -> {
            startActivity(intentFor<Login>().newTask().clearTask())
            true
        }

        R.id.salirOlvidar ->{
            preferencias!!.edit().clear().apply()
            startActivity(intentFor<Login>().newTask().clearTask())
            true
        }
        else -> false
    }
}

