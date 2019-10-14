package com.example.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.ContactsContract
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.prefs.Preferences
import java.util.regex.Pattern

class Login : AppCompatActivity() {

    var preferencias: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val toolbar = find<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolbar)

        preferencias = PreferenceManager.getDefaultSharedPreferences(this@Login)
        preferencias = getSharedPreferences("Preferencias", Context.MODE_PRIVATE)
        preferenciasExist()
        btn_login.onClick {
            val email = edt_email.text.toString()
            val pass = edt_passwor.text.toString()

            if (logeo(email, pass))
                startActivity(intentFor<MainActivity>().newTask().clearTask())
            guardarpreferencias(email, pass)
        }
    }

    fun validaremail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        //Verifica que tenga la estructura de un E-mail
    }

    fun validarPasswor(pass: String): Boolean {
        return pass.length > 5
    }

    fun logeo(email: String, pass: String): Boolean {
        if (!validaremail(email)) {
            toast("E-mail invalido")
            return false
        } else if (!validarPasswor(pass)) {
            toast("Contraseña invalida, debe contener más de 5 caracteres")
            return false
        } else {
            return true
        }
    }

    fun guardarpreferencias(email: String, pass: String) {
        if (sw_recordar.isChecked) {
            preferencias!!.edit()
                .putString("email", email)
                .putString("pass", pass)
                .apply()
        }
    }

    fun preferenciasExist() {
        val email = preferencias!!.getString("email", "")
        val pass = preferencias!!.getString("pass", "")


        if (!email.isNullOrEmpty() && !TextUtils.isEmpty(pass)) {
            edt_email.setText(email)
            edt_passwor.setText(pass)
            sw_recordar.isChecked = true
        }

    }
}
